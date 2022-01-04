package com.example.codeseek_testtask.service;

import com.example.codeseek_testtask.exception.NotEnoughMoneyException;
import com.example.codeseek_testtask.exception.PlayerAlreadyExistsException;
import com.example.codeseek_testtask.exception.PlayerNotFoundException;
import com.example.codeseek_testtask.exception.TheSameTeamException;
import com.example.codeseek_testtask.mapper.PlayerMapper;
import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.model.Player;
import com.example.codeseek_testtask.model.dto.PlayerDTO;
import com.example.codeseek_testtask.repository.FootballTeamRepository;
import com.example.codeseek_testtask.repository.PlayerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PlayerService {
    private static final int COEFFICIENT = 100000;
    private final PlayerRepository playerRepository;
    private final FootballTeamRepository teamRepository;
    private final FootballTeamService teamService;

    public PlayerService(PlayerRepository playerRepository,
                         FootballTeamRepository teamRepository,
                         FootballTeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    public PlayerDTO savePlayer(PlayerDTO playerDTO) {
        FootballTeam team = teamService.findTeam(playerDTO.getTeamId());
        Player newPlayer = PlayerMapper.toEntity(playerDTO);

        if (exists(newPlayer)) {
            throw new PlayerAlreadyExistsException(String.format("The player with name %s already exists",
                    newPlayer.getFullName()));
        } else {
            newPlayer.setTeam(team);
            return PlayerMapper.toDto(playerRepository.save(newPlayer));
        }
    }

    public PlayerDTO updatePlayer(int id, PlayerDTO changedPlayerDTO) {
        Player playerToUpdate = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(String.format("The player with id %d not found", id)));

        playerToUpdate.setFullName(changedPlayerDTO.getFullName());
        playerToUpdate.setAge(changedPlayerDTO.getAge());
        playerToUpdate.setMonthsOfExperience(changedPlayerDTO.getMonthsOfExperience());
        playerToUpdate.setNationality(changedPlayerDTO.getNationality());

        if (exists(playerToUpdate)) {
            throw new PlayerAlreadyExistsException(String.format("The player with name %s already exists",
                    playerToUpdate.getFullName()));
        } else {
            return PlayerMapper.toDto(playerRepository.save(playerToUpdate));
        }
    }

    public PlayerDTO findPlayer(int id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(String.format("The player with id %d not found", id)));
        return PlayerMapper.toDto(player);
    }

    public void deletePlayer(int id) {
        Player playerToDelete = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(String.format("The player with id %d not found", id)));
        playerRepository.delete(playerToDelete);
    }

    private boolean exists(Player player) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIgnoreCase();

        Example<Player> example = Example.of(player, matcher);
        return playerRepository.exists(example);
    }

    @Transactional(rollbackOn = {Exception.class})
    public void playerTransfer(int playerId, int newTeamId) {
        Player playerToTransfer = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(String.format("The player with id %d not found", playerId)));

        FootballTeam oldFootballTeam = playerToTransfer.getTeam();
        FootballTeam newFootballTeam = teamService.findTeam(newTeamId);

        if (oldFootballTeam.getId() == newFootballTeam.getId())
            throw new TheSameTeamException("Transfer to the same team");

        BigDecimal transferPrice = BigDecimal.valueOf(playerToTransfer.getMonthsOfExperience())
                .multiply(BigDecimal.valueOf(COEFFICIENT))
                .divide(BigDecimal.valueOf(playerToTransfer.getAge()), RoundingMode.HALF_UP);

        BigDecimal priceWithTax = transferPrice
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(oldFootballTeam.getTransferTax()))
                .add(transferPrice);

        //check transaction opportunity
        BigDecimal newTeamBalance = newFootballTeam.getAccountBalance();
        if (newTeamBalance.compareTo(priceWithTax) < 0)
            throw new NotEnoughMoneyException(String.format("The team %s doesn't have enough money for the transfer",
                    newFootballTeam.getTeamName()));

        //transfer
        playerToTransfer.setTeam(newFootballTeam);

        //transaction
        newFootballTeam.setAccountBalance(newTeamBalance.subtract(priceWithTax));
        oldFootballTeam.setAccountBalance(oldFootballTeam.getAccountBalance().add(priceWithTax));

        teamRepository.save(oldFootballTeam);
        teamRepository.save(newFootballTeam);
    }
}

package com.example.codeseek_testtask.service;

import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.model.Player;
import com.example.codeseek_testtask.repository.FootballTeamRepository;
import com.example.codeseek_testtask.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final FootballTeamRepository teamRepository;
    private final FootballTeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository,
                         FootballTeamRepository teamRepository,
                         FootballTeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    public List<Player> findPlayersByTeam(Integer teamId) {
        if (teamService.findTeam(teamId) == null) return null;

        /**
         * There are 2 options for how this could be done
         * I liked the 2nd one more
         * since it doesn't depend on the teamService and FootballTeam entity
         * even if we consider that it's slower
         */

//        return teamService.findTeam(teamId).getPlayers().stream()
//                .sorted(Comparator.comparingInt(Player::getId))
//                .collect(Collectors.toList());

        return playerRepository.findAll().stream()
                .filter(player -> player.getTeam().getId() == teamId)
                .sorted(Comparator.comparingInt(Player::getId))
                .collect(Collectors.toList());
    }

    public Player savePlayer(Player player, Integer teamId) {
        FootballTeam team = teamService.findTeam(teamId);
        if (team == null || exists(player)) {
            return null;
        } else {
            team.getPlayers().add(player);
            player.setTeam(team);
            return playerRepository.save(player);
        }
    }

    public Player updatePlayer(Integer id, Player changedPlayer) {
        Player playerToUpdate = this.findPlayer(id);

        if (playerToUpdate == null) {
            return null;
        }

        playerToUpdate.setFullName(changedPlayer.getFullName());
        playerToUpdate.setAge(changedPlayer.getAge());
        playerToUpdate.setMonthsOfExperience(changedPlayer.getMonthsOfExperience());
        playerToUpdate.setNationality(changedPlayer.getNationality());

        if (exists(playerToUpdate)) {
            return null;
        } else {
            return playerRepository.save(playerToUpdate);
        }
    }

    public Player  findPlayer(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }

    public boolean exists(Player player) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIgnoreCase();

        Example<Player> example = Example.of(player, matcher);

        return playerRepository.exists(example);
    }

    @Transactional(rollbackOn = {Exception.class})
    public ResponseEntity<?> playerTransfer(Integer newTeamId, Player player) {
        Player playerToTransfer = findPlayer(player.getId());

        if (playerToTransfer == null)
            return new ResponseEntity<>("We didn't find such a player", HttpStatus.NOT_FOUND);

        FootballTeam oldFootballTeam = teamService.findTeam(playerToTransfer.getTeam().getId());
        FootballTeam newFootballTeam = teamService.findTeam(newTeamId);

        if (oldFootballTeam == null || newFootballTeam == null || oldFootballTeam.getId() == newFootballTeam.getId())
            return new ResponseEntity<>("We didn't find such a team", HttpStatus.NOT_FOUND);

        int transferPrice = (playerToTransfer.getMonthsOfExperience() * 100000) / playerToTransfer.getAge();
        int priceWithTax = (int) (transferPrice + ((transferPrice / 100.0) * oldFootballTeam.getTransferTax()));

        //check transaction opportunity
        int newTeamBalance = newFootballTeam.getAccountBalance();
        if (newTeamBalance < priceWithTax)
            return new ResponseEntity<>("The team " + newFootballTeam.getName() + " doesn't have enough money for the transfer", HttpStatus.NOT_FOUND);

        //transfer
        oldFootballTeam.getPlayers().remove(playerToTransfer);
        playerToTransfer.setTeam(newFootballTeam);
        newFootballTeam.getPlayers().add(playerToTransfer);

        //transaction
        newFootballTeam.setAccountBalance(newTeamBalance - priceWithTax);
        oldFootballTeam.setAccountBalance(oldFootballTeam.getAccountBalance() + priceWithTax);

        teamRepository.save(oldFootballTeam);
        teamRepository.save(newFootballTeam);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

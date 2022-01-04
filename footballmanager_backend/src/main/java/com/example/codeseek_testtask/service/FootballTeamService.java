package com.example.codeseek_testtask.service;

import com.example.codeseek_testtask.exception.TeamAlreadyExistsException;
import com.example.codeseek_testtask.exception.TeamNotFoundException;
import com.example.codeseek_testtask.mapper.FootballTeamMapper;
import com.example.codeseek_testtask.mapper.PlayerMapper;
import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.model.dto.FootballTeamDTO;
import com.example.codeseek_testtask.model.dto.PlayerDTO;
import com.example.codeseek_testtask.repository.FootballTeamRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballTeamService {
    private final FootballTeamRepository teamRepository;

    public FootballTeamService(FootballTeamRepository footballTeamRepository) {
        this.teamRepository = footballTeamRepository;
    }

    public List<FootballTeamDTO> findAllTeams() {
        return teamRepository.findAllByOrderById().stream()
                .map(FootballTeamMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findPlayersByTeam(Integer teamId) {
        FootballTeam team = findTeam(teamId);

        return team.getPlayers()
                .stream()
                .map(PlayerMapper::toDto)
                .sorted(Comparator.comparingInt(PlayerDTO::getId))
                .collect(Collectors.toList());
    }

    public FootballTeamDTO saveTeam(FootballTeamDTO footballTeamDTO) {
        FootballTeam newTeam = FootballTeamMapper.toEntity(footballTeamDTO);

        if (exists(newTeam)) {
            throw new TeamAlreadyExistsException(String.format("The team with name %s already exists",
                    newTeam.getTeamName()));
        } else {
            return FootballTeamMapper.toDto(teamRepository.save(newTeam));
        }
    }

    public FootballTeamDTO updateTeam(Integer id, FootballTeamDTO updatedFootballTeamDTO) {
        FootballTeam teamToUpdate = findTeam(id);

        teamToUpdate.setTeamName(updatedFootballTeamDTO.getTeamName());
        teamToUpdate.setCountry(updatedFootballTeamDTO.getCountry());
        teamToUpdate.setAccountBalance(updatedFootballTeamDTO.getAccountBalance());
        teamToUpdate.setTransferTax(updatedFootballTeamDTO.getTransferTax());


        if (exists(teamToUpdate)) {
            throw new TeamAlreadyExistsException(String.format("The team with name %s already exists",
                    teamToUpdate.getTeamName()));
        } else {
            return FootballTeamMapper.toDto(teamRepository.save(teamToUpdate));
        }
    }

    public FootballTeam findTeam(int id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(String.format("The team with id %d not found", id)));
    }

    private boolean exists(FootballTeam team) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIgnoreCase();

        Example<FootballTeam> example = Example.of(team, matcher);
        return teamRepository.exists(example);
    }

    public void deleteTeam(int id) {
        FootballTeam teamToDelete = findTeam(id);
        teamRepository.delete(teamToDelete);
    }
}

package com.example.codeseek_testtask.service;

import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.repository.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballTeamService {
    private final FootballTeamRepository teamRepository;

    @Autowired
    public FootballTeamService(FootballTeamRepository footballTeamRepository) {
        this.teamRepository = footballTeamRepository;
    }

    public List<FootballTeam> findAllTeams() {
        return teamRepository.findAll().stream()
                .sorted(Comparator.comparing(FootballTeam::getId))
                .collect(Collectors.toList());
    }

    public FootballTeam saveTeam(FootballTeam footballTeam) {
        if (exists(footballTeam)) {
            return null;
        } else {
            return teamRepository.save(footballTeam);
        }
    }

    public FootballTeam updateTeam(Integer id, FootballTeam updatedFootballTeam) {
        FootballTeam teamToUpdate = this.findTeam(id);
        if (teamToUpdate == null) {
            return null;
        }

        teamToUpdate.setName(updatedFootballTeam.getName());
        teamToUpdate.setCountry(updatedFootballTeam.getCountry());
        teamToUpdate.setAccountBalance(updatedFootballTeam.getAccountBalance());
        teamToUpdate.setTransferTax(updatedFootballTeam.getTransferTax());

        return this.saveTeam(teamToUpdate);
    }

    public FootballTeam findTeam(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    public boolean exists(FootballTeam team) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIgnoreCase();

        Example<FootballTeam> example = Example.of(team, matcher);

        return teamRepository.exists(example);
    }

    public void deleteTeam(FootballTeam teamToDelete) {
        teamRepository.delete(teamToDelete);
    }
}

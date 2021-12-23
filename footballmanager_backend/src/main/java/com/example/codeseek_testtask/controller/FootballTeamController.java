package com.example.codeseek_testtask.controller;

import com.example.codeseek_testtask.mapper.FootballTeamMapper;
import com.example.codeseek_testtask.model.DTO.FootballTeamTO;
import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/footballTeams")
public class FootballTeamController {
    private final FootballTeamService service;

    @Autowired
    public FootballTeamController(FootballTeamService footballTeamService) {
        this.service = footballTeamService;
    }

    @GetMapping("")
    public ResponseEntity<List<FootballTeamTO>> getAllTeams() {
        List<FootballTeamTO> teams = service.findAllTeams().stream()
                .map(FootballTeamMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballTeamTO> getTeamById(@PathVariable("id") Integer id) {
        FootballTeam team = service.findTeam(id);

        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(FootballTeamMapper.toDto(team), HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<FootballTeamTO> addTeam(@Valid @RequestBody FootballTeam footballTeam,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        FootballTeam newTeam = service.saveTeam(footballTeam);

        if (newTeam == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(FootballTeamMapper.toDto(newTeam), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballTeamTO> updateTeam(@PathVariable Integer id,
                                                   @Valid @RequestBody FootballTeam changedFootballTeam,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        FootballTeam updatedFootballTeam = service.updateTeam(id, changedFootballTeam);

        if (updatedFootballTeam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(FootballTeamMapper.toDto(updatedFootballTeam), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Integer id) {

        FootballTeam teamToDelete = service.findTeam(id);

        if (teamToDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.deleteTeam(teamToDelete);
            return new ResponseEntity<>(teamToDelete, HttpStatus.OK);
        }
    }
}

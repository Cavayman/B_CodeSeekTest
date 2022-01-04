package com.example.codeseek_testtask.controller;

import com.example.codeseek_testtask.mapper.FootballTeamMapper;
import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.model.dto.FootballTeamDTO;
import com.example.codeseek_testtask.model.dto.PlayerDTO;
import com.example.codeseek_testtask.service.FootballTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/footballTeams")
public class FootballTeamController {
    private final FootballTeamService service;

    public FootballTeamController(FootballTeamService footballTeamService) {
        this.service = footballTeamService;
    }

    @GetMapping
    public List<FootballTeamDTO> getAllTeams() {
        return service.findAllTeams();
    }

    @GetMapping("/{id}")
    public FootballTeamDTO getTeamById(@PathVariable int id) {
        FootballTeam team = service.findTeam(id);
        return FootballTeamMapper.toDto(team);
    }

    @GetMapping("/{teamId}/players")
    public List<PlayerDTO> getPlayersByTeam(@PathVariable int teamId) {
        return service.findPlayersByTeam(teamId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FootballTeamDTO addTeam(@Valid @RequestBody FootballTeamDTO footballTeamDTO) {
        return service.saveTeam(footballTeamDTO);
    }

    @PutMapping("/{id}")
    public FootballTeamDTO updateTeam(@PathVariable int id,
                                      @Valid @RequestBody FootballTeamDTO changedFootballTeamDTO) {
        return service.updateTeam(id, changedFootballTeamDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable int id) {
        service.deleteTeam(id);
    }
}

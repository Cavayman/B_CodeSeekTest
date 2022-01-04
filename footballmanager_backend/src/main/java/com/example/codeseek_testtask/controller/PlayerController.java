package com.example.codeseek_testtask.controller;

import com.example.codeseek_testtask.model.dto.PlayerDTO;
import com.example.codeseek_testtask.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService playerService) {
        this.service = playerService;
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable int id) {
        return service.findPlayer(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PlayerDTO savePlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return service.savePlayer(playerDTO);
    }

    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@PathVariable int id,
                                  @Valid @RequestBody PlayerDTO changedPlayerDTO) {
        return service.updatePlayer(id, changedPlayerDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        service.deletePlayer(id);
    }

    @PostMapping("/{playerId}/transfer/{newTeamId}")
    public void playerTransfer(@PathVariable int playerId,
                               @PathVariable int newTeamId) {
        service.playerTransfer(playerId, newTeamId);
    }
}
package com.example.codeseek_testtask.controller;

import com.example.codeseek_testtask.mapper.PlayerMapper;
import com.example.codeseek_testtask.model.DTO.PlayerTO;
import com.example.codeseek_testtask.model.Player;
import com.example.codeseek_testtask.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.service = playerService;
    }

    @GetMapping("/all/{teamId}")
    public ResponseEntity<List<PlayerTO>> getPlayers(@PathVariable("teamId") Integer teamId) {
        List<Player> players = service.findPlayersByTeam(teamId);

        List<PlayerTO> playersTO = players == null ? null : players.stream()
                .map(PlayerMapper::toDto)
                .collect(Collectors.toList());

        if (playersTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(playersTO, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerTO> getPlayerById(@PathVariable("id") Integer id) {
        Player player = service.findPlayer(id);

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(PlayerMapper.toDto(player), HttpStatus.OK);
        }
    }

    @PostMapping("/{teamId}")
    public ResponseEntity<PlayerTO> addPlayer(@Valid @RequestBody Player player,
                                            @PathVariable("teamId") Integer teamId,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player newPlayer = service.savePlayer(player, teamId);

        if (newPlayer == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(PlayerMapper.toDto(newPlayer), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerTO> updatePlayer(@PathVariable Integer id,
                                               @Valid @RequestBody Player changedPlayer,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player updatedPlayer = service.updatePlayer(id, changedPlayer);

        if (updatedPlayer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(PlayerMapper.toDto(updatedPlayer), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") Integer id) {
        Player playerToDelete = service.findPlayer(id);

        if (playerToDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.deletePlayer(playerToDelete);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/transfer/{newTeamId}")
    public ResponseEntity<?> playerTransfer(@PathVariable Integer newTeamId,
                                                 @Valid @RequestBody Player playerToTransfer,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation error", HttpStatus.BAD_REQUEST);
        }

        return service.playerTransfer(newTeamId, playerToTransfer);
    }
}
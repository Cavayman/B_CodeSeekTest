package com.example.codeseek_testtask.mapper;

import com.example.codeseek_testtask.model.Player;
import com.example.codeseek_testtask.model.dto.PlayerDTO;

public class PlayerMapper {
    public static PlayerDTO toDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();

        playerDTO.setId(player.getId());
        playerDTO.setFullName(player.getFullName());
        playerDTO.setAge(player.getAge());
        playerDTO.setMonthsOfExperience(player.getMonthsOfExperience());
        playerDTO.setNationality(player.getNationality());

        return playerDTO;
    }

    public static Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();

        player.setId(playerDTO.getId());
        player.setFullName(playerDTO.getFullName());
        player.setAge(playerDTO.getAge());
        player.setMonthsOfExperience(playerDTO.getMonthsOfExperience());
        player.setNationality(playerDTO.getNationality());

        return player;
    }
}

package com.example.codeseek_testtask.mapper;

import com.example.codeseek_testtask.model.DTO.PlayerTO;
import com.example.codeseek_testtask.model.Player;

public class PlayerMapper {
    public static PlayerTO toDto(Player player) {
        int id = player.getId();
        String fullName = player.getFullName();
        int age = player.getAge();
        short monthsOfExperience = player.getMonthsOfExperience();
        String nationality = player.getNationality();

        return new PlayerTO(id, fullName, age, monthsOfExperience, nationality);
    }
}

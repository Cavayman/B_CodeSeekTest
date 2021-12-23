package com.example.codeseek_testtask.mapper;

import com.example.codeseek_testtask.model.DTO.FootballTeamTO;
import com.example.codeseek_testtask.model.FootballTeam;


public class FootballTeamMapper {
    public static FootballTeamTO toDto(FootballTeam team) {
        int id = team.getId();
        String name = team.getName();
        String country = team.getCountry();
        int accountBalance = team.getAccountBalance();
        float transferTax = team.getTransferTax();

        return new FootballTeamTO(id, name, country, accountBalance, transferTax);
    }
}

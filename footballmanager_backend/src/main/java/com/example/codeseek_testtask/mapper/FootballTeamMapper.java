package com.example.codeseek_testtask.mapper;

import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.model.dto.FootballTeamDTO;

public class FootballTeamMapper {
    public static FootballTeamDTO toDto(FootballTeam team) {
        FootballTeamDTO footballTeamDTO = new FootballTeamDTO();

        footballTeamDTO.setId(team.getId());
        footballTeamDTO.setTeamName(team.getTeamName());
        footballTeamDTO.setCountry(team.getCountry());
        footballTeamDTO.setAccountBalance(team.getAccountBalance());
        footballTeamDTO.setTransferTax(team.getTransferTax());

        return footballTeamDTO;
    }

    public static FootballTeam toEntity(FootballTeamDTO footballTeamDTO) {
        FootballTeam footballTeam = new FootballTeam();

        footballTeam.setId(footballTeamDTO.getId());
        footballTeam.setTeamName(footballTeamDTO.getTeamName());
        footballTeam.setCountry(footballTeamDTO.getCountry());
        footballTeam.setAccountBalance(footballTeamDTO.getAccountBalance());
        footballTeam.setTransferTax(footballTeamDTO.getTransferTax());

        return footballTeam;
    }
}

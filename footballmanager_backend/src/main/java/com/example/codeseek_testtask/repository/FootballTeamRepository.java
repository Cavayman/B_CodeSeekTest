package com.example.codeseek_testtask.repository;

import com.example.codeseek_testtask.model.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam, Integer> { }

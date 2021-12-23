package com.example.codeseek_testtask.repository;

import com.example.codeseek_testtask.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> { }

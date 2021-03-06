package com.example.codeseek_testtask.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private int age;
    private short monthsOfExperience;
    private String nationality;
    @ManyToOne
    private FootballTeam team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public short getMonthsOfExperience() {
        return monthsOfExperience;
    }

    public void setMonthsOfExperience(short monthsOfExperience) {
        this.monthsOfExperience = monthsOfExperience;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public FootballTeam getTeam() {
        return team;
    }

    public void setTeam(FootballTeam team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", monthsOfExperience=" + monthsOfExperience +
                ", nationality='" + nationality + '\'' +
                ", team=" + team.getTeamName() +
                '}';
    }
}

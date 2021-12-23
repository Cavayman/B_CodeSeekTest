package com.example.codeseek_testtask.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp = ".*[^0-9].*", message = "Cannot contain only numbers")
    @Length(min = 4, message = "Full name must be at least 4 characters")
    private String fullName;
    @Min(value = 18, message = "Cannot be less than 18")
    @Max(value = 65, message = "Cannot be greater than 65")
    private int age;
    @Min(value = 0, message = "Cannot be negative")
    @Max(value = 564, message = "Cannot be greater than 564")
    private short monthsOfExperience;
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp = "[^0-9]+", message = "Cannot contain numbers")
    @Length(min = 3, message = "Nationality must be at least 3 characters")
    @Length(max = 25, message = "Nationality can't be longer than 25 characters")
    private String nationality;
    @ManyToOne()
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private FootballTeam team;

    public Player() {
    }

    public Player(String fullName, int age, short monthsOfExperience, String nationality) {
        this.fullName = fullName;
        this.age = age;
        this.monthsOfExperience = monthsOfExperience;
        this.nationality = nationality;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return age == player.age && monthsOfExperience == player.monthsOfExperience && Objects.equals(fullName, player.fullName) && Objects.equals(nationality, player.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age, monthsOfExperience, nationality);
    }
}

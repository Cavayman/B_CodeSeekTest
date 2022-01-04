package com.example.codeseek_testtask.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class PlayerDTO {
    private int id;
    @Length(min = 4, message = "Full name must be at least 4 characters")
    private String fullName;
    @Min(value = 18, message = "Cannot be less than 18")
    @Max(value = 65, message = "Cannot be greater than 65")
    private int age;
    @Min(value = 0, message = "Cannot be negative")
    @Max(value = 564, message = "Cannot be greater than 564")
    private short monthsOfExperience;
    @Pattern(regexp = "[^0-9]*", message = "Cannot contain numbers")
    @Length(min = 3, message = "Nationality must be at least 3 characters")
    @Length(max = 25, message = "Nationality can't be longer than 25 characters")
    private String nationality;
    @Min(value = 0, message = "Cannot be negative")
    private int teamId;

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

    public int getTeamId() {
        return teamId;
    }
}

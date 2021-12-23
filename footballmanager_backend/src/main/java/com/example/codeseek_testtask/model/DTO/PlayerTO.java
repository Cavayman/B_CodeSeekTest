package com.example.codeseek_testtask.model.DTO;

public class PlayerTO {
    private int id;
    private String fullName;
    private int age;
    private short monthsOfExperience;
    private String nationality;

    public PlayerTO() {
    }

    public PlayerTO(Integer id, String fullName, int age, short monthsOfExperience, String nationality) {
        this.id = id;
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
}

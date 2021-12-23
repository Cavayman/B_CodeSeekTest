package com.example.codeseek_testtask.model.DTO;

public class FootballTeamTO {
    int id;
    String name;
    String country;
    int accountBalance;
    float transferTax;

    public FootballTeamTO() {
    }

    public FootballTeamTO(int id, String name, String country, int accountBalance, float transferTax) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.accountBalance = accountBalance;
        this.transferTax = transferTax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public float getTransferTax() {
        return transferTax;
    }

    public void setTransferTax(float transferTax) {
        this.transferTax = transferTax;
    }

}

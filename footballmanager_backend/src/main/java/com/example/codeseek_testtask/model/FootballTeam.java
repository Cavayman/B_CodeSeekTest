package com.example.codeseek_testtask.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "football_teams")
public class FootballTeam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private String country;
    private BigDecimal accountBalance;
    private float transferTax;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", orphanRemoval = true)
    private Set<Player> players = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public float getTransferTax() {
        return transferTax;
    }

    public void setTransferTax(float transferTax) {
        this.transferTax = transferTax;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", name='" + teamName + '\'' +
                ", country='" + country + '\'' +
                ", accountBalance=" + accountBalance +
                ", transferTax=" + transferTax +
                ", players=" + players.toString() +
                '}';
    }
}

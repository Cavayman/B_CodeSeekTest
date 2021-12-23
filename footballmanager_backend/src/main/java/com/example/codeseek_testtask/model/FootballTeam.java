package com.example.codeseek_testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "football_teams")
public class FootballTeam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp = ".*[^0-9].*", message = "Cannot contain only numbers")
    private String name;
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp = "[^0-9]+", message = "Cannot contain numbers")
    @Length(max = 25, message = "Maximum length 25 characters")
    private String country;
    @Min(0)
    private int accountBalance;
    @Min(value = 0, message = "Value must be in the range 0-10")
    @Max(value = 10, message = "Value must be in the range 0-10")
    private float transferTax;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", orphanRemoval = true)
    private Set<Player> players = new HashSet<>();

    public FootballTeam() {
    }

    public FootballTeam(String name, String country, int accountBalance, float transferTax) {
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

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballTeam)) return false;
        FootballTeam that = (FootballTeam) o;
        return accountBalance == that.accountBalance && Float.compare(that.transferTax, transferTax) == 0 && name.equals(that.name) && country.equals(that.country) && players.equals(that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, accountBalance, transferTax, players);
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", accountBalance=" + accountBalance +
                ", transferTax=" + transferTax +
                ", players=" + players +
                '}';
    }
}

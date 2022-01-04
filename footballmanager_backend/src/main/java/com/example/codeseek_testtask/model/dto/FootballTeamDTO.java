package com.example.codeseek_testtask.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class FootballTeamDTO {
    private int id;
    @NotBlank(message = "Cannot be empty")
    private String teamName;
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp = "[^0-9]*", message = "Cannot contain numbers")
    @Length(max = 25, message = "Maximum length 25 characters")
    private String country;
    @NotNull(message = "Account balance cannot be null")
    @Min(value = 0, message = "Account balance cannot be negative")
    private BigDecimal accountBalance;
    @Min(value = 0, message = "Value must be in the range 0-10")
    @Max(value = 10, message = "Value must be in the range 0-10")
    private float transferTax;

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
}

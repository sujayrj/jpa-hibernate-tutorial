package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "SAVING_ACCOUNT")
@Entity
public class SavingsAccount extends Account {
    private Double interest;

    public SavingsAccount(Double balance, Double interest) {
        super(balance);
        this.interest = interest;
    }
}

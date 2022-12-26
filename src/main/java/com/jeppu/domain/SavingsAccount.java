package com.jeppu.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("savings")
public class SavingsAccount extends Account{
    private Double APY;
    public SavingsAccount(Double balance, Double APY){
        super(balance);
        this.APY=APY;
    }
}

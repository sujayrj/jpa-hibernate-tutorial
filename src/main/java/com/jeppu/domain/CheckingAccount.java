package com.jeppu.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("checking")
public class CheckingAccount extends Account{
    private Double overdraftLimit;
    public CheckingAccount(Double balance, Double overdraftLimit){
        super(balance);
        this.overdraftLimit=overdraftLimit;
    }

}

package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "CHECKING_ACCOUNT")
@Entity
public class CheckingAccount extends Account{
    @Column(name = "[limit]")
    private Double limit;

    public CheckingAccount(Double balance, Double limit) {
        super(balance);
        this.limit = limit;
    }
}

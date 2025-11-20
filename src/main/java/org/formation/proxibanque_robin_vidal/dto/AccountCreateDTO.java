package org.formation.proxibanque_robin_vidal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountCreateDTO {
    private String accountNumber;
    private Double balance;
    private Date openingDate;
    private Long customerId;
    private Double overdraftLimit;
    private Double interestRate;
}


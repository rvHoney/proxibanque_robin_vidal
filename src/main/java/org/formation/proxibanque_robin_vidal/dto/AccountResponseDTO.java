package org.formation.proxibanque_robin_vidal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private Double balance;
    private Date openingDate;
    private Long customerId;
    private Double overdraftLimit;
    private Double interestRate;
}


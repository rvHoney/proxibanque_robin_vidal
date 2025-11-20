package org.formation.proxibanque_robin_vidal.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
}


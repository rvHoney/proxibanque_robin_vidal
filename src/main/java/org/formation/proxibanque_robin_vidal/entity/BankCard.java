package org.formation.proxibanque_robin_vidal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}


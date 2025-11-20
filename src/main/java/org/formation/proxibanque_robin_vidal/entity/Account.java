package org.formation.proxibanque_robin_vidal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    private Double balance;

    @Temporal(TemporalType.DATE)
    private Date openingDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double overdraftLimit = 1000.0;

    private Double interestRate = 0.03;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankCard> bankCards;
}


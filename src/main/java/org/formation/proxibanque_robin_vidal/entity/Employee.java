package org.formation.proxibanque_robin_vidal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne 
    @JoinColumn(name = "agency_id")
    private Agency agency;
}


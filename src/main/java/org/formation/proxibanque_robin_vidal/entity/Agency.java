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
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, unique = true, nullable = false)
    private String authCode;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

//    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
//    private List<Employee> employees;

    public Agency(String authCode, Date creationDate) {
        this.authCode = authCode;
        this.creationDate = creationDate;
    }
}


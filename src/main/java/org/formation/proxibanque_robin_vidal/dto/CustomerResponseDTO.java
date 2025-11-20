package org.formation.proxibanque_robin_vidal.dto;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;
    private Long employeeId;
}


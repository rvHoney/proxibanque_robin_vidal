package org.formation.proxibanque_robin_vidal.dto;

import lombok.Data;
import org.formation.proxibanque_robin_vidal.entity.Role;

@Data
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private Long agencyId;
}


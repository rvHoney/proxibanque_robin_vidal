package org.formation.proxibanque_robin_vidal.service;

import org.formation.proxibanque_robin_vidal.dto.EmployeeCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.EmployeeResponseDTO;
import org.formation.proxibanque_robin_vidal.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeResponseDTO> getEmployees();
    EmployeeCreateDTO createEmployee(EmployeeCreateDTO employeeDTO);
    Optional<EmployeeResponseDTO> getEmployee(Long id);
    EmployeeResponseDTO replaceEmployee(Long id, Employee employee);
}

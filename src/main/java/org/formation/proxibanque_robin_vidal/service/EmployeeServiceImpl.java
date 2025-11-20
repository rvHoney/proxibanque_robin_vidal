package org.formation.proxibanque_robin_vidal.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.dto.EmployeeCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.EmployeeResponseDTO;
import org.formation.proxibanque_robin_vidal.entity.Advisor;
import org.formation.proxibanque_robin_vidal.entity.Agency;
import org.formation.proxibanque_robin_vidal.entity.Employee;
import org.formation.proxibanque_robin_vidal.entity.Manager;
import org.formation.proxibanque_robin_vidal.entity.Role;
import org.formation.proxibanque_robin_vidal.repository.AgencyRepository;
import org.formation.proxibanque_robin_vidal.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AgencyRepository agencyRepository;

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeCreateDTO createEmployee(EmployeeCreateDTO employeeDTO) {
        Agency agency = agencyRepository.findById(employeeDTO.getAgencyId())
                .orElseThrow(() -> new RuntimeException("Agency not: " + employeeDTO.getAgencyId()));

        Employee employee;
        if (employeeDTO.getRole() == Role.ADVISOR) {
            Advisor advisor = new Advisor();
            employee = advisor;
        } else if (employeeDTO.getRole() == Role.MANAGER) {
            Manager manager = new Manager();
            employee = manager;
        } else {
            throw new RuntimeException("Invalid role: " + employeeDTO.getRole());
        }

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setLogin(employeeDTO.getLogin());
        employee.setPassword(employeeDTO.getPassword());
        employee.setRole(employeeDTO.getRole());
        employee.setAgency(agency);

        Employee savedEmployee = employeeRepository.save(employee);
        
        // Mettre à jour le DTO avec l'ID généré
        employeeDTO.setId(savedEmployee.getId());
        return employeeDTO;
    }

    @Override
    public Optional<EmployeeResponseDTO> getEmployee(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    @Override
    public EmployeeResponseDTO replaceEmployee(Long id, Employee employee) {
        employee.setId(id);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToResponseDTO(savedEmployee);
    }

    private EmployeeResponseDTO convertToResponseDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setLogin(employee.getLogin());
        dto.setRole(employee.getRole());
        dto.setAgencyId(employee.getAgency() != null ? employee.getAgency().getId() : null);
        return dto;
    }
}

package org.formation.proxibanque_robin_vidal.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.dto.EmployeeCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.EmployeeResponseDTO;
import org.formation.proxibanque_robin_vidal.entity.Employee;
import org.formation.proxibanque_robin_vidal.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("employees")
    List<EmployeeResponseDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("employees")
    EmployeeCreateDTO createEmployee(@RequestBody EmployeeCreateDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("employees/{id}")
    ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> replaceEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        EmployeeResponseDTO employeeReplaced = employeeService.replaceEmployee(id, employee);
        return this.getEmployee(employeeReplaced.getId());
    }
}
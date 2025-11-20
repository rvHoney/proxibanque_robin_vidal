package org.formation.proxibanque_robin_vidal.controller;

import lombok.RequiredArgsConstructor;
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
    List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("employees")
    Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("employees/{id}")
    ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> replaceEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee employeeReplaced = employeeService.replaceEmployee(id, employee);
        return this.getEmployee(employeeReplaced.getId());
    }
}
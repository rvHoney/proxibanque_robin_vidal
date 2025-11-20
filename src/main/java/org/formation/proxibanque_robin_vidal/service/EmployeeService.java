package org.formation.proxibanque_robin_vidal.service;

import org.formation.proxibanque_robin_vidal.entity.Agency;
import org.formation.proxibanque_robin_vidal.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee createEmployee(Employee employee);
    Optional<Employee> getEmployee(Long id);
    Employee replaceEmployee(Long id, Employee employee);
}

package org.formation.proxibanque_robin_vidal.repository;

import org.formation.proxibanque_robin_vidal.entity.Employee;
import org.formation.proxibanque_robin_vidal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.employee.id = :employeeId")
    long countCustomersByEmployeeId(@Param("employeeId") Long employeeId);
}

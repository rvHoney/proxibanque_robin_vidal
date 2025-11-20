package org.formation.proxibanque_robin_vidal.repository;

import org.formation.proxibanque_robin_vidal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmployeeId(Long employeeId);
}


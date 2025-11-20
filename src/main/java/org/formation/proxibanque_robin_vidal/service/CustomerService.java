package org.formation.proxibanque_robin_vidal.service;

import org.formation.proxibanque_robin_vidal.dto.CustomerCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.CustomerResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerResponseDTO> getAllCustomers();
    Optional<CustomerResponseDTO> getCustomerById(Long id);
    List<CustomerResponseDTO> getEmployeeCustomers(Long employeeId);
    CustomerResponseDTO createCustomer(Long employeeId, CustomerCreateDTO customerDTO);
    CustomerResponseDTO updateCustomer(Long id, CustomerCreateDTO customerDTO);
    void deleteCustomer(Long id);
    long getCustomerCount(Long employeeId);
}


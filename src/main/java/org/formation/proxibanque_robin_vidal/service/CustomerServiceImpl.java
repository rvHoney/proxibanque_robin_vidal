package org.formation.proxibanque_robin_vidal.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.dto.CustomerCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.CustomerResponseDTO;
import org.formation.proxibanque_robin_vidal.entity.Customer;
import org.formation.proxibanque_robin_vidal.entity.Employee;
import org.formation.proxibanque_robin_vidal.repository.CustomerRepository;
import org.formation.proxibanque_robin_vidal.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final int MAX_CUSTOMERS_PER_EMPLOYEE = 10;
    
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerResponseDTO> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<CustomerResponseDTO> getEmployeeCustomers(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException());
        
        return customerRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(Long employeeId, CustomerCreateDTO customerDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException());
        
        // Vérifier la limite de 10 clients
        long customerCount = employeeRepository.countCustomersByEmployeeId(employeeId);
        if (customerCount >= MAX_CUSTOMERS_PER_EMPLOYEE) {
            throw new RuntimeException();
        }
        
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setZipCode(customerDTO.getZipCode());
        customer.setCity(customerDTO.getCity());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmployee(employee);
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    @Override
    @Transactional
    public CustomerResponseDTO updateCustomer(Long id, CustomerCreateDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setZipCode(customerDTO.getZipCode());
        customer.setCity(customerDTO.getCity());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        
        customerRepository.delete(customer);
    }

    @Override
    public long getCustomerCount(Long employeeId) {
        return employeeRepository.countCustomersByEmployeeId(employeeId);
    }

    private CustomerResponseDTO convertToDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAddress(customer.getAddress());
        dto.setZipCode(customer.getZipCode());
        dto.setCity(customer.getCity());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setEmployeeId(customer.getEmployee() != null ? customer.getEmployee().getId() : null);
        return dto;
    }
}


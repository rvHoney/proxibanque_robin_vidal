package org.formation.proxibanque_robin_vidal.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.dto.CustomerCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.CustomerResponseDTO;
import org.formation.proxibanque_robin_vidal.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employees/{employeeId}")
    List<CustomerResponseDTO> getEmployeeCustomers(@PathVariable Long employeeId) {
        return customerService.getEmployeeCustomers(employeeId);
    }

    @PostMapping("/employees/{employeeId}")
    CustomerResponseDTO createCustomer(@PathVariable Long employeeId, @RequestBody CustomerCreateDTO customerDTO) {
        return customerService.createCustomer(employeeId, customerDTO);
    }

    @PutMapping("/{id}")
    CustomerResponseDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees/{employeeId}/count")
    ResponseEntity<Long> getCustomerCount(@PathVariable Long employeeId) {
        return ResponseEntity.ok(customerService.getCustomerCount(employeeId));
    }
}


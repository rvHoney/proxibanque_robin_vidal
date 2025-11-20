package org.formation.proxibanque_robin_vidal.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.dto.AccountCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.AccountResponseDTO;
import org.formation.proxibanque_robin_vidal.dto.TransferDTO;
import org.formation.proxibanque_robin_vidal.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{accountNumber}")
    ResponseEntity<AccountResponseDTO> getAccountByNumber(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customers/{customerId}")
    List<AccountResponseDTO> getCustomerAccounts(@PathVariable Long customerId) {
        return accountService.getCustomerAccounts(customerId);
    }

    @PostMapping
    AccountResponseDTO createAccount(@RequestBody AccountCreateDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @PutMapping("/{id}")
    AccountResponseDTO updateAccount(@PathVariable Long id, @RequestBody AccountCreateDTO accountDTO) {
        return accountService.updateAccount(id, accountDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfer")
    ResponseEntity<Void> transfer(@RequestBody TransferDTO transferDTO) {
        accountService.transfer(transferDTO);
        return ResponseEntity.ok().build();
    }
}


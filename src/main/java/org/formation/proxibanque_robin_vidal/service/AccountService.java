package org.formation.proxibanque_robin_vidal.service;

import org.formation.proxibanque_robin_vidal.dto.AccountCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.AccountResponseDTO;
import org.formation.proxibanque_robin_vidal.dto.TransferDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountResponseDTO> getAllAccounts();
    Optional<AccountResponseDTO> getAccountById(Long id);
    Optional<AccountResponseDTO> getAccountByNumber(String accountNumber);
    List<AccountResponseDTO> getCustomerAccounts(Long customerId);
    AccountResponseDTO createAccount(AccountCreateDTO accountDTO);
    AccountResponseDTO updateAccount(Long id, AccountCreateDTO accountDTO);
    void deleteAccount(Long id);
    void transfer(TransferDTO transferDTO);
}


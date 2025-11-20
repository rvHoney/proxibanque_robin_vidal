package org.formation.proxibanque_robin_vidal.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.dto.AccountCreateDTO;
import org.formation.proxibanque_robin_vidal.dto.AccountResponseDTO;
import org.formation.proxibanque_robin_vidal.dto.TransferDTO;
import org.formation.proxibanque_robin_vidal.entity.Account;
import org.formation.proxibanque_robin_vidal.entity.Customer;
import org.formation.proxibanque_robin_vidal.repository.AccountRepository;
import org.formation.proxibanque_robin_vidal.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountResponseDTO> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public Optional<AccountResponseDTO> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(this::convertToDTO);
    }

    @Override
    public List<AccountResponseDTO> getCustomerAccounts(Long customerId) {
        return accountRepository.findByCustomerId(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountResponseDTO createAccount(AccountCreateDTO accountDTO) {
        Customer customer = customerRepository.findById(accountDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException());

        if (accountRepository.findByAccountNumber(accountDTO.getAccountNumber()).isPresent()) {
            throw new RuntimeException();
        }

        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance() != null ? accountDTO.getBalance() : 0.0);
        account.setOpeningDate(accountDTO.getOpeningDate());
        account.setCustomer(customer);
        account.setOverdraftLimit(accountDTO.getOverdraftLimit() != null ? accountDTO.getOverdraftLimit() : 1000.0);
        account.setInterestRate(accountDTO.getInterestRate() != null ? accountDTO.getInterestRate() : 0.03);

        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    @Override
    @Transactional
    public AccountResponseDTO updateAccount(Long id, AccountCreateDTO accountDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        if (!account.getAccountNumber().equals(accountDTO.getAccountNumber())) {
            if (accountRepository.findByAccountNumber(accountDTO.getAccountNumber()).isPresent()) {
                throw new RuntimeException();
            }
        }

        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setOpeningDate(accountDTO.getOpeningDate());
        account.setOverdraftLimit(accountDTO.getOverdraftLimit());
        account.setInterestRate(accountDTO.getInterestRate());

        if (accountDTO.getCustomerId() != null && !accountDTO.getCustomerId().equals(account.getCustomer().getId())) {
            Customer customer = customerRepository.findById(accountDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException());
            account.setCustomer(customer);
        }

        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        accountRepository.delete(account);
    }

    @Override
    @Transactional
    public void transfer(TransferDTO transferDTO) {
        if (transferDTO.getAmount() == null || transferDTO.getAmount() <= 0) {
            throw new RuntimeException();
        }

        Account fromAccount = accountRepository.findById(transferDTO.getFromAccountId())
                .orElseThrow(() -> new RuntimeException());

        Account toAccount = accountRepository.findById(transferDTO.getToAccountId())
                .orElseThrow(() -> new RuntimeException());

        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new RuntimeException();
        }

        Double minimumBalance = -fromAccount.getOverdraftLimit();
        Double newBalance = fromAccount.getBalance() - transferDTO.getAmount();

        if (newBalance < minimumBalance) {
            throw new RuntimeException();
        }

        fromAccount.setBalance(newBalance);
        toAccount.setBalance(toAccount.getBalance() + transferDTO.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    private AccountResponseDTO convertToDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        dto.setOpeningDate(account.getOpeningDate());
        dto.setCustomerId(account.getCustomer() != null ? account.getCustomer().getId() : null);
        dto.setOverdraftLimit(account.getOverdraftLimit());
        dto.setInterestRate(account.getInterestRate());
        return dto;
    }
}


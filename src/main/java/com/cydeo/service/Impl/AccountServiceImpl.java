package com.cydeo.service.Impl;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Component
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {
        // we need to create an object
        Account account = Account.builder().id(UUID.randomUUID()).balance(balance).creationDate(createDate)
                .accountType(accountType).userId(userId)
                .accountStatus(AccountStatus.ACTIVE).build();// accountStatus is active as default when created
        // saving and returning that account
        return accountRepository.saveAccount(account);
    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }
}

package com.cydeo.service;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public interface AccountService {
    Account createAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId);
    List<Account> listAllAccount();
    void deleteAccount(UUID accountId);

    void activateAccount(UUID id);

    Account retrieveAccountById(UUID sender);
}

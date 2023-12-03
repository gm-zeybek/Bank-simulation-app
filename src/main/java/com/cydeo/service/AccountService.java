package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import com.cydeo.enums.AccountType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface AccountService {
    AccountDTO createAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId);
    List<AccountDTO> listAllAccount();
    void deleteAccount(Long accountId);

    void activateAccount(Long id);

    AccountDTO retrieveAccountById(Long sender);
}

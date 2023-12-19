package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountService {
    void createNewAccount(AccountDTO accountDTO);
    List<AccountDTO> listAllAccount();
    void deleteAccount(Long accountId);

    void activateAccount(Long id);

    AccountDTO retrieveAccountById(Long sender);

    List<AccountDTO> listAllActiveAccounts();

    void updateAccount(AccountDTO accountDTO);
}

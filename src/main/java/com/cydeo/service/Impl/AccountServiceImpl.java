package com.cydeo.service.Impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import com.cydeo.mapper.AccountMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        accountDTO.setCreationDate(new Date());
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        //save into the database(repository)
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }

    @Override
    public List<AccountDTO> listAllAccount() {

        // we are getting accounts from db and converting to dto and returning as a list
        return accountRepository.findAll().stream().map(accountMapper::convertToDTO).collect(Collectors.toList());

    }


    @Override
    public void deleteAccount(Long accountId) {
        // find account from db
        Account account = accountRepository.findById(accountId).get();
        // set status as deleted
        account.setAccountStatus(AccountStatus.DELETED);
        // save account db
        accountRepository.save(account);
    }

    @Override
    public void activateAccount(Long id) {
        // find account from db
        Account account = accountRepository.findById(id).get();
        // set status as active
        account.setAccountStatus(AccountStatus.ACTIVE);
        // save account db
        accountRepository.save(account);
    }

    @Override
    public AccountDTO retrieveAccountById(Long id) {
        // find the account based on id and convert to entity and return
        return accountMapper.convertToDTO(accountRepository.findById(id).get());
    }

    @Override
    public List<AccountDTO> listAllActiveAccounts() {
        // new bussiness function added for account status active
        return accountRepository.findAccountsByAccountStatus(AccountStatus.ACTIVE)
                .stream().map(accountMapper::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }
}

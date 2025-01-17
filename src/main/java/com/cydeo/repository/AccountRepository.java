package com.cydeo.repository;

import com.cydeo.exceptions.RecordNotFoundException;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {
   public static List<Account> accountList = new ArrayList<>();

    public Account saveAccount(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
       return accountList;
    }

    public Account findById(UUID id) {// responsibility of this method finds the id if exist in database otherwise throw an exception
       //write a method that find an id inside the list if not throw RecordNotFountException
            return accountList.stream().filter(account -> account.getId().equals(id)).findFirst()
                    .orElseThrow(() ->new RecordNotFoundException("Account does not have in the databasse"));

    }
}

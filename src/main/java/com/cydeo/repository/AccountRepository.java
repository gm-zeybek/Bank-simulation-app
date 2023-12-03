package com.cydeo.repository;

import com.cydeo.dto.AccountDTO;
import com.cydeo.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {
   public static List<AccountDTO> accountDTOList = new ArrayList<>();

    public AccountDTO saveAccount(AccountDTO accountDTO){
        accountDTOList.add(accountDTO);
        return accountDTO;
    }

    public List<AccountDTO> findAll() {
       return accountDTOList;
    }

    public AccountDTO findById(UUID id) {// responsibility of this method finds the id if exist in database otherwise throw an exception
       //write a method that find an id inside the list if not throw RecordNotFountException
            return accountDTOList.stream().filter(account -> account.getId().equals(id)).findFirst()
                    .orElseThrow(() ->new RecordNotFoundException("Account does not have in the databasse"));

    }
}

package com.cydeo.repository;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.exceptions.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//   public static List<AccountDTO> accountDTOList = new ArrayList<>();
//
//    public AccountDTO saveAccount(AccountDTO accountDTO){
//        accountDTOList.add(accountDTO);
//        return accountDTO;
//    }
//
//    public List<AccountDTO> findAll() {
//       return accountDTOList;
//    }
//
//    public AccountDTO findById(Long id) {// responsibility of this method finds the id if exist in database otherwise throw an exception
//       //write a method that find an id inside the list if not throw RecordNotFountException
//            return accountDTOList.stream().filter(account -> account.getId().equals(id)).findFirst()
//                    .orElseThrow(() ->new RecordNotFoundException("Account does not have in the databasse"));
//
//    }
}

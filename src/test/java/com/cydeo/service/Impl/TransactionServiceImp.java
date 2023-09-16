package com.cydeo.service.Impl;

import com.cydeo.exceptions.BadRequestException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImp implements TransactionService {
    private final AccountRepository accountRepository; // use a habit for new dependency injection always be private final

    public TransactionServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*

            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance to make transfer
            -if both accounts are checking, if not, one of them saving, it needs to be same userId
        */

        validateAccount(sender,receiver);


        return null;
    }

    private void validateAccount(Account sender, Account receiver){

        /*
            -if any of the account is null
            -if account ids are the same (same account)
            -if the account exist in the database (repository)

        */

        if(sender==null || receiver == null){
            throw new BadRequestException("Sender or Receiver can not be null");
        }

        // check if the sender account and receiver accounts are same throw badrequestException
        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender and Receiver account can not be same");
        }

        // verify from database if accounts are exist in the database
        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) {
        // findByid method should be common for database if certain account id exist in database
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}

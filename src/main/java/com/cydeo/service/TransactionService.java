package com.cydeo.service;

import com.cydeo.exceptions.UnderConstructionException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public interface TransactionService {
    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) throws UnderConstructionException;

    List<Transaction> findAllTransaction();

    List<Transaction> lastTenTransaction();

    void saveTransaction(Transaction transaction);

    List<Transaction> findTransactionsById(UUID accountId);
}

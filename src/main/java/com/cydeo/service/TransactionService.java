package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.exceptions.UnderConstructionException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface TransactionService {
    TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) throws UnderConstructionException;

    List<TransactionDTO> findAllTransaction();

    List<TransactionDTO> lastTenTransaction();

    void saveTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> findTransactionsById(Long accountId);
}

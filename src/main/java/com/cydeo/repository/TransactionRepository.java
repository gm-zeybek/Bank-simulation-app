package com.cydeo.repository;

import com.cydeo.model.Transaction;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionList;
    }

    public List<Transaction> findTransactionsByAccountId(UUID accountId) {
        // if account id is used as a sender or receiver return those transactions
        return transactionList.stream()
                .filter(transaction -> transaction.getSender().equals(accountId) || transaction.getReceiver().equals(accountId))
                .collect(Collectors.toList());
    }
}

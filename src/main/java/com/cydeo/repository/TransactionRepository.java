package com.cydeo.repository;

import com.cydeo.dto.TransactionDTO;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    List<TransactionDTO> transactionDTOList = new ArrayList<>();

    public TransactionDTO save(TransactionDTO transactionDTO){
        transactionDTOList.add(transactionDTO);
        return transactionDTO;
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionDTOList;
    }

    public List<TransactionDTO> findTransactionsByAccountId(UUID accountId) {
        // if account id is used as a sender or receiver return those transactions
        return transactionDTOList.stream()
                .filter(transactionDTO -> transactionDTO.getSender().equals(accountId) || transactionDTO.getReceiver().equals(accountId))
                .collect(Collectors.toList());
    }
}

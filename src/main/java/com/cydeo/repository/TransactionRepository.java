package com.cydeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // optional
public interface TransactionRepository extends JpaRepository {

//    List<TransactionDTO> transactionDTOList = new ArrayList<>();
//
//    public TransactionDTO save(TransactionDTO transactionDTO){
//        transactionDTOList.add(transactionDTO);
//        return transactionDTO;
//    }
//
//    public List<TransactionDTO> getAllTransactions() {
//        return transactionDTOList;
//    }
//
//    public List<TransactionDTO> findTransactionsByAccountId(Long accountId) {
//        // if account id is used as a sender or receiver return those transactions
//        return transactionDTOList.stream()
//                .filter(transactionDTO -> transactionDTO.getSender().equals(accountId) || transactionDTO.getReceiver().equals(accountId))
//                .collect(Collectors.toList());
//    }
}

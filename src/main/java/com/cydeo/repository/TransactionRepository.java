package com.cydeo.repository;

import com.cydeo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository // optional
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query(value = "SELECT * FROM transactions ORDER BY create_date DESC LIMIT 10", nativeQuery = true)
    public List<Transaction> lastTenTransaction();

    // we are looking for sender or receiver transactions
    @Query("SELECT t FROM Transaction t WHERE t.sender.id = ?1 OR t.receiver.id = ?1")
    public List<Transaction> findTransactionsByAccountId(Long id);


    @Query("SELECT t FROM Transaction t")
    public List<Transaction> findAllTransaction();

}

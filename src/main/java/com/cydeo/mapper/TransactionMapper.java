package com.cydeo.mapper;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.entity.Account;
import com.cydeo.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public TransactionDTO convertDTO(Transaction transaction){
        return modelMapper.map(transaction,TransactionDTO.class);
    }

    public Transaction convertToEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO, Transaction.class);
    }

}

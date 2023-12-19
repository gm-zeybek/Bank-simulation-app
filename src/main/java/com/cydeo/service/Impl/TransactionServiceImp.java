package com.cydeo.service.Impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.enums.AccountType;
import com.cydeo.exceptions.AccountOwnerShipException;
import com.cydeo.exceptions.BadRequestException;
import com.cydeo.exceptions.BalanceNotSufficientException;
import com.cydeo.exceptions.UnderConstructionException;
import com.cydeo.mapper.AccountMapper;
import com.cydeo.mapper.TransactionMapper;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@SuppressWarnings("unchecked")
@Component
public class TransactionServiceImp implements TransactionService {
    @Value("${under_construction}")
    private  boolean underConstruction;
    private final AccountService accountService; // use a habit for new dependency injection always be private final

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    public TransactionServiceImp(AccountService accountService, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) throws UnderConstructionException {
        /*

            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance to make transfer
            -if both accounts are checking, if not, one of them saving, it needs to be same userId
            -if service is not under construction
        */

        if (!underConstruction) {
            validateAccount(amount, sender, receiver);

            // after validation is done and money is transferred to the receiver account
            // we need to create the transaction object and save and return to database

            TransactionDTO transactionDTO = new TransactionDTO(sender,receiver,amount,message,creationDate);


            // save and return the transaction
             transactionRepository.save(transactionMapper.convertToEntity(transactionDTO));
             return transactionDTO;
        }else {
            throw new UnderConstructionException("service is under construction please try later");
        }
    }


    private void validateAccount(BigDecimal amount, AccountDTO sender, AccountDTO receiver){

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
        
        checkAccountOwnership(sender,receiver);

        executeBalanceAndUpdateIfRequired(amount, sender, receiver);



    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        // write if statement for check sender or receiver is saving account and ownership of the accounts are not the same
        // throw the AccountOwnershipException
        if((sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
                && !sender.getUserId().equals(receiver.getUserId())){
                throw new AccountOwnerShipException("Account should be same for transaction to/from saving account");

        }

    }

    private void findAccountById(Long id) {
        // findByid method should be common for database if certain account id exist in database
        accountService.retrieveAccountById(id);
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver){

        if(checkSenderBalance(sender,amount)){
            //update sender and receiver account
            // 100 - 80 = 20
            sender.setBalance(sender.getBalance().subtract(amount));
            // 50 + 80 = 130
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new BalanceNotSufficientException("Balance is not sufficient to make this transfer");
        }
        AccountDTO senderAcc = accountService.retrieveAccountById(sender.getId());
        AccountDTO receiverAcc = accountService.retrieveAccountById(receiver.getId());
        senderAcc.setBalance(sender.getBalance());
        receiverAcc.setBalance(receiver.getBalance());
        accountService.updateAccount(senderAcc);
        accountService.updateAccount(receiverAcc);

    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        // verify sender has enough amount to make the transfer
//        return sender.getBalance().compareTo(amount) >= 0;
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0;
    }

     public List<TransactionDTO> findAllTransaction() {
        return  transactionRepository.findAllTransaction().stream()
                .map(transactionMapper::convertDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<TransactionDTO> lastTenTransaction() {
        return transactionRepository.lastTenTransaction().stream()
                .map(transactionMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void saveTransaction(TransactionDTO transactionDTO) {
        if(transactionDTO.getCreateDate()==null){
            transactionDTO.setCreateDate(new Date());
        }

        transactionRepository.save(transactionMapper.convertToEntity(transactionDTO));
    }

    @Override
    public List<TransactionDTO> findTransactionsById(Long accountId) {
        return transactionRepository.findTransactionsByAccountId(accountId)
                .stream().map(transactionMapper::convertDTO)
                .collect(Collectors.toList());
    }
}

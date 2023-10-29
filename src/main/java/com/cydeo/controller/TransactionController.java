package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;


    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping("/make-transfer")
    public String makeTransfer(Model model){
        // empty transaction object
        model.addAttribute("transaction", Transaction.builder().build());
        // list of account;
        model.addAttribute("accounts",accountService.listAllAccount());
        // list of last 10 transaction
        model.addAttribute("transactions", transactionService.lastTenTransaction());
        return "/transaction/make-transfer";
    }
    @PostMapping("/make-transfer")
    public String insertTransfer(Transaction transaction,  Model model){
        // empty transaction object
        model.addAttribute("transaction",
                Transaction.builder().sender(transaction.getSender())
                        .receiver(transaction.getReceiver())
                        .amount(transaction.getAmount())
                        .createDate(transaction.getCreateDate())
                        .message(transaction.getMessage())
                        .build());
//        Account sender = accountService.retrieveAccountById(transaction.getSender());
//        Account receiver = accountService.retrieveAccountById(transaction.getReceiver());
//        model.addAttribute("transaction",transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage()));
        // list of acount;
        model.addAttribute("accounts",accountService.listAllAccount());
        transactionService.saveTransaction(transaction);
        // list of last 10 transaction
        model.addAttribute("transactions", transactionService.lastTenTransaction());

        return "redirect:/make-transfer";
    }
    @GetMapping("/transaction/{accountId}")
    public String getTransaction(@PathVariable("accountId")UUID accountId, Model model){
        System.out.println(accountId);
        model.addAttribute("transactions", transactionService.findTransactionsById(accountId));
        return "/transaction/transactions";
    }


}

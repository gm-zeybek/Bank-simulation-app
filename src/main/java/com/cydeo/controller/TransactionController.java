package com.cydeo.controller;

import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
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
        // list of acount;
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
        // list of acount;
        model.addAttribute("accounts",accountService.listAllAccount());
        transactionService.saveTransaction(transaction);
        // list of last 10 transaction
        model.addAttribute("transactions", transactionService.lastTenTransaction());

        return "redirect:/make-transfer";
    }


}

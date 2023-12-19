package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;


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
        model.addAttribute("transactionDTO", new TransactionDTO());
        // list of account;
        model.addAttribute("accounts",accountService.listAllActiveAccounts());
        // list of last 10 transaction
        model.addAttribute("transactions", transactionService.lastTenTransaction());
        return "/transaction/make-transfer";
    }
    @PostMapping("/make-transfer")
    public String insertTransfer(@ModelAttribute("transaction") @Valid TransactionDTO transactionDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("accounts",accountService.listAllAccount());
            // list of last 10 transaction
            model.addAttribute("transactions", transactionService.lastTenTransaction());
            return "/transaction/make-transfer";
        }
        AccountDTO sender = accountService.retrieveAccountById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.retrieveAccountById(transactionDTO.getReceiver().getId());
        model.addAttribute("transactionDTO",transactionService.makeTransfer(sender,receiver,transactionDTO.getAmount(),new Date(),transactionDTO.getMessage()));

        return "redirect:/make-transfer";
    }
    @GetMapping("/transaction/{accountId}")
    public String getTransaction(@PathVariable("accountId") Long accountId, Model model){
        System.out.println(accountId);
        model.addAttribute("transactions", transactionService.findTransactionsById(accountId));
        return "/transaction/transactions";
    }


}

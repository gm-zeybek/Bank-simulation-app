package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
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
        model.addAttribute("transaction", new TransactionDTO());
        // list of account;
        model.addAttribute("accounts",accountService.listAllAccount());
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
        // empty transaction object
//        model.addAttribute("transaction",
//                TransactionDTO.builder().sender(transactionDTO.getSender())
//                        .receiver(transactionDTO.getReceiver())
//                        .amount(transactionDTO.getAmount())
//                        .createDate(transactionDTO.getCreateDate())
//                        .message(transactionDTO.getMessage())
//                        .build());
        AccountDTO sender = accountService.retrieveAccountById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.retrieveAccountById(transactionDTO.getReceiver().getId());
        model.addAttribute("transaction",transactionService.makeTransfer(sender,receiver,transactionDTO.getAmount(),new Date(),transactionDTO.getMessage()));
        // list of acount;
//        model.addAttribute("accounts",accountService.listAllAccount());
//        transactionService.saveTransaction(transactionDTO);
//        // list of last 10 transaction
//        model.addAttribute("transactions", transactionService.lastTenTransaction());

        return "redirect:/make-transfer";
    }
    @GetMapping("/transaction/{accountId}")
    public String getTransaction(@PathVariable("accountId")UUID accountId, Model model){
        System.out.println(accountId);
        model.addAttribute("transactions", transactionService.findTransactionsById(accountId));
        return "/transaction/transactions";
    }


}

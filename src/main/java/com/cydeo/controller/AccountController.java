package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model){

        model.addAttribute("accountList",accountService.listAllAccount());

        return "account/index";
    }
    @GetMapping("/create-form")
    public String createAccount(Model model){

        // we need to provide empty account object
        model.addAttribute("account",Account.builder().build());
        // we need to provide account type enums object
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/create")
    public String postAccountInfo(@ModelAttribute("account")Account account, Model model){
        // create a method to capture information from ui
        // print out account information
        // create new Account method populating information using the data captured from ui
        // return index page
        accountService.createAccount(account.getBalance(),new Date(),account.getAccountType(),account.getUserId());
        System.out.println(account);


        return "redirect:/index";
    }
}

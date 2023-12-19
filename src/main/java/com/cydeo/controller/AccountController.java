package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.dto.AccountDTO;
import com.cydeo.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model) {

        model.addAttribute("accountList", accountService.listAllAccount());

        return "account/index";
    }

    @GetMapping("/create-form")
    public String createAccount(Model model) {

        // we need to provide empty account object
        model.addAttribute("accountDTO", new AccountDTO());
        // we need to provide account type enums object
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/create")
    public String postAccountInfo(@Valid @ModelAttribute("account") AccountDTO accountDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("accountTypes", AccountType.values());
            return "account/create-account";
        }
        // create a method to capture information from ui
        // print out account information
        // create new Account method populating information using the data captured from ui
        // return index page
        accountService.createNewAccount(accountDTO);
//        System.out.println(account);


        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id, Model model){
        System.out.println(id);
        accountService.deleteAccount(id);

        return "redirect:/index";
    }
    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") Long id){
        System.out.println(id);
        accountService.activateAccount(id);

        return "redirect:/index";
    }
}

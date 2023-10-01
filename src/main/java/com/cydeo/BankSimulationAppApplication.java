package com.cydeo;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);
        Account sender = accountService.createAccount(BigDecimal.valueOf(120), new Date(), AccountType.CHECKING, 1L);
        Account receiver = accountService.createAccount(BigDecimal.valueOf(0), new Date(), AccountType.CHECKING, 2L);

//        System.out.println(accountService.listAllAccount());
        transactionService.makeTransfer(sender, receiver, BigDecimal.valueOf(20), new Date(), "rent");
//        System.out.println(accountService.listAllAccount());


    }

}

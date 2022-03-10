package com.rs.businessaccount.controller;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/save")
    public Mono<BankAccount> saveBusinessAcountBank(@RequestBody BankAccount bankAccount){
        return bankAccountService.saveBusinessAccount(bankAccount);
    }

    @GetMapping("/all")
    public Flux<BankAccount> findAll(){
        return bankAccountService.finAlllBusinessAccount();
    }
}

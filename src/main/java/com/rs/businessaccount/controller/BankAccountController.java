package com.rs.businessaccount.controller;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.service.BankAccountService;
import com.rs.businessaccount.vo.AccountBalance;
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

    @PutMapping("/save")
    public Mono<BankAccount> updateBusinessAccountBank(@RequestBody BankAccount bankAccount){
        return bankAccountService.saveBusinessAccount(bankAccount);
    }
    @GetMapping("/{account}")
    public Mono<BankAccount> findBankAccountByAccountNumber(@PathVariable("account") Integer accountNumber){
        return bankAccountService.findBankAccountByAccountNumber(accountNumber);
    }
    @GetMapping("/status/{number}")
    public Mono<Boolean> existAccount(@PathVariable("number") Integer accountNumber){
        return bankAccountService.exitAccount(accountNumber);
    }

    @GetMapping("/balance/{account}")
    public Mono<AccountBalance> getBalanceOfAccount(@PathVariable("account") Integer accountNumber){
        return bankAccountService.getBalanceOfAccount(accountNumber);
    }
}

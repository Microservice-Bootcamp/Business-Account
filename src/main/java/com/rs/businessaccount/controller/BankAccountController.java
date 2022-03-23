package com.rs.businessaccount.controller;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.service.BankAccountService;
import com.rs.businessaccount.vo.AccountBalance;
import com.rs.businessaccount.vo.ResumeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/save")
    public Mono<ResponseEntity<BankAccount>> saveBusinessAcountBank(@RequestBody BankAccount bankAccount){
        return bankAccountService.saveBusinessAccount(bankAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<BankAccount>> findAll(){
        return new ResponseEntity<>(bankAccountService.finAlllBusinessAccount(), HttpStatus.OK);

    }

    @PutMapping("/save")
    public Mono<ResponseEntity<BankAccount>> updateBusinessAccountBank(@RequestBody BankAccount bankAccount){
        return bankAccountService.updateBankAccount(bankAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @GetMapping("/{account}")
    public Mono<ResponseEntity< BankAccount>> findBankAccountByAccountNumber(@PathVariable("account") Integer accountNumber){
        return bankAccountService.findBankAccountByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
    @GetMapping("/status/{number}")
    public Mono<Boolean> existAccount(@PathVariable("number") Integer accountNumber){
        return bankAccountService.exitAccount(accountNumber);
    }

    @GetMapping("/balance/{account}")
    public Mono<ResponseEntity<AccountBalance>> getBalanceOfAccount(@PathVariable("account") Integer accountNumber){
        return bankAccountService.getBalanceOfAccount(accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/resume/{dniNumber}")
    public Mono<ResumeProduct> consolidateProductOfUser(@PathVariable("dniNumber") Integer dniNumber){
        return bankAccountService.consolidateAccountByDni(dniNumber);
    }
}

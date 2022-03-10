package com.rs.businessaccount.service;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountService {

    private String userType = "business";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Mono<BankAccount> saveBusinessAccount(BankAccount bankAccount){
        if(bankAccount.getTypeAccount().equals("corriente")){
            return bankAccountRepository.save(bankAccount);
        }
        return Mono.empty();
    }

    public Flux<BankAccount> finAlllBusinessAccount(){
        return bankAccountRepository.findAll();
    }
}

package com.rs.businessaccount.service;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.repository.BankAccountRepository;
import com.rs.businessaccount.vo.AccountBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Mono<BankAccount> saveBusinessAccount(BankAccount bankAccount){
        if(bankAccount.getTypeAccount().equals("corriente")){
            return bankAccountRepository.save(bankAccount);
        }
        return Mono.empty();
    }


    public Mono<BankAccount> updateBankAccount(BankAccount bankAccount){
        if(bankAccount.getTypeAccount().equals("corriente")){
            return bankAccountRepository.existsByIdBankAccount(bankAccount.getIdBankAccount())
                    .flatMap(condition->{
                        if(condition){
                            return bankAccountRepository.save(bankAccount);
                        }
                        return Mono.empty();
                    });
        }
        return Mono.empty();

    }

    public Flux<BankAccount> finAlllBusinessAccount(){
        return bankAccountRepository.findAll();
    }

    public Mono<BankAccount> findBankAccountByAccountNumber(Integer accountNumber){
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }
    public Mono<Boolean> exitAccount(Integer accountNumber){
        return bankAccountRepository.existsByAccountNumber(accountNumber);
    }
    public Mono<AccountBalance> getBalanceOfAccount(Integer accountNumber){
        return bankAccountRepository.findByAccountNumber(accountNumber, "true");
    }

}

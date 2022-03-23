package com.rs.businessaccount.repository;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.repository.custom.CustomBusinessAccountRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@EnableReactiveMongoRepositories
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String>, CustomBusinessAccountRepository {
    Mono<BankAccount> findByAccountNumber(Integer accountNumber);
    Mono<Boolean> existsByAccountNumber(Integer accountNumber);
    Flux<BankAccount> findAllByDniUser(Integer dniUser);
    Mono<Boolean> existsByIdBankAccount(String idAccount);
    Flux<BankAccount> findAllByDniUser(Integer dniNumber);
}

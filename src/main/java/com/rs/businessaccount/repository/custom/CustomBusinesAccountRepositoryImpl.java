package com.rs.businessaccount.repository.custom;

import com.rs.businessaccount.entity.BankAccount;

import com.rs.businessaccount.vo.AccountBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import reactor.core.publisher.Mono;

public class CustomBusinesAccountRepositoryImpl implements CustomBusinessAccountRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public CustomBusinesAccountRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<AccountBalance> findByAccountNumber(Integer getOneValor, String parameter) {
        Query query = new Query(Criteria.where("accountNumber").is(getOneValor));
        query.fields().include("balance");
        return reactiveMongoTemplate.findOne(query, AccountBalance.class);
    }
}

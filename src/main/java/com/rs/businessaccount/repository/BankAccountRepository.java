package com.rs.businessaccount.repository;

import com.rs.businessaccount.entity.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {
}

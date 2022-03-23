package com.rs.businessaccount.repository;

import com.rs.businessaccount.entity.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
}

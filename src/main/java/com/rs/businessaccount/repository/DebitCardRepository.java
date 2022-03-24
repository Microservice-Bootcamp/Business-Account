package com.rs.businessaccount.repository;

import com.rs.businessaccount.entity.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {

    Mono<DebitCard> findByCardNumber(String cardNumber);
}

package com.rs.businessaccount.service;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.entity.DebitCard;
import com.rs.businessaccount.repository.BankAccountRepository;
import com.rs.businessaccount.repository.DebitCardRepository;
import com.rs.businessaccount.vo.AccountBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Mono<DebitCard> saveDebitCard(DebitCard debitCard) {
        return bankAccountRepository.findAllByDniUser(debitCard.getDniUser())
                .collectList()
                .switchIfEmpty(Mono.empty())
                .flatMap(x -> {
                    log.info("Initialization of save method bl...");
                    BankAccount principalAccount = x.stream().filter(account -> account.getAccountNumber().equals(debitCard.getPrincipalBankAccount())).findFirst().orElse(null);

                    if(principalAccount == null) {
                        return Mono.empty();
                    }

                    debitCard.setCardNumber(UUID.randomUUID().toString());

                    x.forEach(account -> debitCard.addBankAccount(account.getAccountNumber()));

                    return debitCardRepository.save(debitCard);
                });
    }

    public Flux<DebitCard> findAll() {
        return debitCardRepository.findAll();
    }
}

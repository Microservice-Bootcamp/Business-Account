package com.rs.businessaccount.service;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.entity.DebitCard;
import com.rs.businessaccount.repository.BankAccountRepository;
import com.rs.businessaccount.repository.DebitCardRepository;
import com.rs.businessaccount.vo.AccountBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Mono<DebitCard> saveDebitCard(DebitCard debitCard) {
        return bankAccountRepository.findAllByDniUser(debitCard.getDniUser()).collectList().switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> {
                    BankAccount principalAccount = x.stream().filter(account -> account.getAccountNumber() == debitCard.getPrincipalBankAccount()).findFirst().orElse(null);

                    if(principalAccount == null) {
                        return Mono.empty();
                    }

                    x.forEach(account -> debitCard.addBankAccount(account.getAccountNumber()));

                    return debitCardRepository.save(debitCard);
                });
    }
}

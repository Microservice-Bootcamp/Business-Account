package com.rs.businessaccount.service;

import com.rs.businessaccount.entity.BankAccount;
import com.rs.businessaccount.repository.BankAccountRepository;
import com.rs.businessaccount.util.WebClientTemplate;
import com.rs.businessaccount.vo.AccountBalance;
import com.rs.businessaccount.vo.UserCredit;
import com.rs.businessaccount.vo.VipBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private WebClientTemplate webClientTemplate;

    public Mono<BankAccount> saveBusinessAccount(BankAccount bankAccount){
        return validateSaveWithCriteria(bankAccount);
    }


    public Mono<BankAccount> updateBankAccount(BankAccount bankAccount){
        /*if(bankAccount.getTypeAccount().equals("corriente")){
            return bankAccountRepository.existsByIdBankAccount(bankAccount.getIdBankAccount())
                    .flatMap(condition->{
                        if(condition){
                            return bankAccountRepository.save(bankAccount);
                        }
                        return Mono.empty();
                    });
        }
        return Mono.empty();*/
        return validateSaveWithCriteria(bankAccount);
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

    private Mono<VipBusiness> isUserBusinessVip(Integer dniNumber){
        return webClientTemplate.templateWebClient("http://localhost:8092")
                .get()
                .uri("/user/business/"+dniNumber)
                .retrieve()
                .bodyToMono(VipBusiness.class);
    }

    private Mono<UserCredit> userBusinessVipHaveCredit(Integer dniNumber){
        return webClientTemplate.templateWebClient("http://localhost:8093")
                .get()
                .uri("/credit/status/"+dniNumber)
                .retrieve()
                .bodyToMono(UserCredit.class);
    }

    private Mono<BankAccount> validateSaveWithCriteria(BankAccount bankAccount){
        return isUserBusinessVip(bankAccount.getDniUser())
                .flatMap(condition-> userBusinessVipHaveCredit(bankAccount.getDniUser())
                        .flatMap(haveCredit->{
                            if(bankAccount.getBalance()>0 && typeAccount.test(bankAccount.getTypeAccount())){
                                if(condition.getStatus().equals(true) && haveCredit.getStatus().equals(true)){
                                    bankAccount.setBenefitStatus(true);
                                }
                                return bankAccountRepository.save(bankAccount);
                            }
                            return Mono.empty();
                        }));
    }

    Predicate<String> typeAccount = type->type.equals("corriente");

}

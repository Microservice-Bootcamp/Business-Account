package com.rs.businessaccount.repository.custom;


import com.rs.businessaccount.vo.AccountBalance;
import reactor.core.publisher.Mono;


public interface CustomBusinessAccountRepository {
Mono<AccountBalance> findByAccountNumber(Integer getOneValor, String parameter);
}

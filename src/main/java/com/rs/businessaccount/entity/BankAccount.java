package com.rs.businessaccount.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "bankAccount")
public class BankAccount {
    /*
    @Id
    private String idCredit;

    private List<String> idTitular;
    private Integer accountNumber;
    private Integer balance;
    private Integer creditLimit;
    private Integer debt;
    private String typeCredit;

     */

    @Id
    private String idBankAccount;

    private List<String> idUser;
    private Integer accountNumber;
    private Integer balance;
    private String typeAccount;
    private Integer maintenanceCharge;
    private Integer movementNumber;
}

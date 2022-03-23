package com.rs.businessaccount.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bankAccount")
public class DebitCard {

    @Id
    private String idDebitCard;

    private String cardNumber;

    private List<Integer> bankAccounts = new ArrayList<>();

    private Integer principalBankAccount;

    private Integer dniUser;

    private Boolean isActive;

    public void addBankAccount(Integer bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

}

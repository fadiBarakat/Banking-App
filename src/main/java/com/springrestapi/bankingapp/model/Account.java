package com.springrestapi.bankingapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Account {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(name = "account_Holder_name")
    private String accountHolderName;
    private int nationalId;
    private double balance;

}

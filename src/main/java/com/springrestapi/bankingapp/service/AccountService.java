package com.springrestapi.bankingapp.service;

import com.springrestapi.bankingapp.model.Account;
import com.springrestapi.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    AccountRepository accRepo;

    @Autowired
    public AccountService(AccountRepository accRepo) {
        this.accRepo = accRepo;
    }


    public List<Account> getAllAccounts() {
        List<Account> accList = accRepo.findAll();
        return accList;

    }

    public Account getById(Long id) {
        Account acc = accRepo.findById(id).get();
        return acc;

    }

    public Account addAccount(Account account) {


        boolean b1 = accRepo.existsByAccountHolderName(account.getAccountHolderName());

        boolean b2 = accRepo.existsByNationalId(account.getNationalId());


        if (b1 && b2) {
            throw new IllegalStateException("An Account Holder With  exactly the Same Info exists");
        } else if (b2) {
            throw new IllegalStateException("Student With the Same National Id " + account.getNationalId() + " already exists");

        }


        Account new_St = accRepo.save(account);
        return new_St;


    }

    public Account deposit(Long id, double amount) {


        if (amount > 0) {
            boolean b = accRepo.existsById(id);
            if (b) {
                Account tempAcc = accRepo.findById(id).get();
                double oldbalance = tempAcc.getBalance();
                tempAcc.setBalance(oldbalance + amount);
                Account newAccount = accRepo.save(tempAcc);
                return newAccount;


            } else {
                throw new IllegalStateException("Account With the id " + id + " doesn't exist");


            }

        } else {
            throw new IllegalStateException("Please Enter a valid amount ");


        }

    }


    public Account withdraw(Long id, double amount) {


        if (amount > 0) {
            boolean b = accRepo.existsById(id);
            if (b) {
                Account tempAcc = accRepo.findById(id).get();
                double oldbalance = tempAcc.getBalance();

                if(amount<oldbalance)
                {
                    tempAcc.setBalance(oldbalance - amount);
                    Account newAccount = accRepo.save(tempAcc);
                    return newAccount;

                }
                else {
                    throw new IllegalStateException("Insufficient Balance, You can't withdraw  = " + amount + " ");

                }

            } else {
                throw new IllegalStateException("Account With the id " + id + " doesn't exist");


            }

        } else {
            throw new IllegalStateException("Please Enter a valid amount ");


        }

    }



    public void delete(Long id) {


        boolean b = accRepo.existsById(id);

        if (b) {
            Account tempAcc = accRepo.findById(id).get();
            accRepo.delete(tempAcc);


        } else {
            throw new IllegalStateException("Account With the id " + id + " doesn't exist");


        }
    }










}

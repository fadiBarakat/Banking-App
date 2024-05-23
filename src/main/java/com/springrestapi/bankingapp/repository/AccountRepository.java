package com.springrestapi.bankingapp.repository;

import com.springrestapi.bankingapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account , Long> {

    boolean existsByAccountHolderName(String Name);
    boolean existsByNationalId (int no);


}

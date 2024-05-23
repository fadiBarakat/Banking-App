package com.springrestapi.bankingapp.controller;

import com.springrestapi.bankingapp.model.Account;
import com.springrestapi.bankingapp.service.AccountService;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }




    @PostMapping("/add")

    public ResponseEntity<Account> addNewAccount(@RequestBody  Account account)
    {
        return  new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public  ResponseEntity<Account> findById(@PathVariable long id)
    {
        return new ResponseEntity<>(accountService.getById(id),HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Account>> finAll()
    {
        List<Account>accountList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.delete(id);
        return ResponseEntity.ok("Account with id = "+id+ "  has been deleted successfully  ");
    }

   @PutMapping("/deposit/{id}")
   public ResponseEntity<Account> deposit (@PathVariable Long id , @RequestBody Map<String,Double> request)
    {
       // return new ResponseEntity<>(accountService.deposit(id,amount),HttpStatus.OK);
       Double amount = request.get("amounte");
        Account acc=accountService.deposit(id,amount);
        return  ResponseEntity.ok(acc);

    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<Account> withdraw (@PathVariable Long id , @RequestBody Map<String,Double> request)
    {
        // return new ResponseEntity<>(accountService.deposit(id,amount),HttpStatus.OK);
        Double amount = request.get("amounte");
        Account acc=accountService.withdraw(id,amount);
        return  ResponseEntity.ok(acc);

    }

}

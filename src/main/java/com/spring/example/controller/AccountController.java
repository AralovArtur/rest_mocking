package com.spring.example.controller;

import com.spring.example.model.Account;
import com.spring.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return new ResponseEntity<Account>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/accounts/")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        service.create(account);
        return new ResponseEntity<>("Account is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody Account updatedAccount) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Account not found", HttpStatus.NOT_FOUND);
        } else {
            service.update(updatedAccount);
            return new ResponseEntity<String>("Account is updated successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Account not found", HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity<String>("Account is deleted successfully", HttpStatus.OK);
        }
    }
}

package com.spring.example.service;

import com.spring.example.model.Account;
import com.spring.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public Account findById(Long id) {
        return repository.getOne(id);
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public void create(Account account) {
        repository.save(account);
    }

    public void update(Account updatedAccount) {
        Account accountTobeUpdated = repository.getOne(updatedAccount.getId());
        accountTobeUpdated.setName(updatedAccount.getName());
        accountTobeUpdated.setEmail(updatedAccount.getEmail());
        repository.save(accountTobeUpdated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
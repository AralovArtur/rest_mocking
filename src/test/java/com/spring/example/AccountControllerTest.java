package com.spring.example;

import com.spring.example.controller.AccountController;
import com.spring.example.model.Account;
import com.spring.example.repository.AccountRepository;
import com.spring.example.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {

    @InjectMocks
    AccountController controller;

    @Mock
    AccountService service;

    @Mock
    Account account;

    private static final long ID = 1;
    private static final String email = "artur.aralov@gmail.com";
    private static final String name = "artur";

    @Test
    public void getAccountByIdTest() {
        when(service.findById(ID)).thenReturn(account);
        assertEquals(controller.getAccountById(ID).getBody(), account);
    }

    @Test
    public void getAccountsTest() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        when(service.getAll()).thenReturn(accounts);
        assertEquals(controller.getAllAccounts().getBody().size(), accounts.size());
    }

    @Test
    public void createAccountTest() {
        assertEquals(controller.createAccount(account).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void updateAccountTest() {
        // Account is not present
        when(service.findById(ID)).thenReturn(null);
        assertEquals(controller.updateAccount(ID, account).getStatusCode(), HttpStatus.NOT_FOUND);

        //Account is present
        when(service.findById(ID)).thenReturn(account);
        assertEquals(controller.updateAccount(ID, account).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteAccountTest() {
        // Account is not present
        when(service.findById(ID)).thenReturn(null);
        assertEquals(controller.deleteAccount(ID).getStatusCode(), HttpStatus.NOT_FOUND);

        //Account is present
        when(service.findById(ID)).thenReturn(account);
        assertEquals(controller.deleteAccount(ID).getStatusCode(), HttpStatus.OK);
    }
}
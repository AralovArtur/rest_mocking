package com.spring.example;

import com.spring.example.model.Account;
import com.spring.example.repository.AccountRepository;
import com.spring.example.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService service;

    @Mock
    AccountRepository repository;

    private static final long ID = 1;
    private static final String email = "artur.aralov@gmail.com";
    private static final String name = "artur";

    @Test
    public void findBydTest() {
        service.findById(ID);
        verify(repository).getOne(ID);
    }

    @Test
    public void getAllTest() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    public void saveTest() {
        Account account = mock(Account.class);
        service.create(account);
        verify(repository).save(account);
    }

    @Test
    public void updateTest() {
        Account account = mock(Account.class);
        when(account.getId()).thenReturn(ID);
        when(account.getName()).thenReturn(name);
        when(account.getEmail()).thenReturn(email);
        when(repository.getOne(ID)).thenReturn(account);
        service.update(account);
        verify(repository).save(account);
    }

    @Test
    public void deleteTest() {
        service.delete(ID);
        verify(repository).deleteById(ID);
    }

}

package com.learn.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.demo.Account;
import com.learn.demo.dao.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }
}

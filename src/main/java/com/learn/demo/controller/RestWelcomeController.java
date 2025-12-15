package com.learn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.demo.Account;
import com.learn.demo.exception.UserNotFoundException;
import com.learn.demo.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class RestWelcomeController {

    @Autowired
    private AccountService accountService;

    // GET account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Integer id) {
        Account account = accountService.findById(id);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(account);
    }

    // CREATE account (POST)
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        Account saved = accountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // UPDATE account
    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account)
            throws UserNotFoundException {

        // Check if exists first
        if (accountService.findById(account.getId()) == null) {
            throw new UserNotFoundException();
        }

        Account updated = accountService.save(account);
        return ResponseEntity.ok(updated);
    }

    // DELETE account
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam Integer id) {

        Account existing = accountService.findById(id);

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account not found");
        }

        accountService.delete(id);

        return ResponseEntity.ok("Account deleted successfully");
    }
}

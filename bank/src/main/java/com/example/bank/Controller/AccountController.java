package com.example.bank.Controller;

import com.example.bank.Model.Account;
import com.example.bank.Model.User;
import com.example.bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking_system/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/save")
    public Account saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.searchById(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/{id}")
    public String removeAccount(@PathVariable Long id) {
        return accountService.removeAccountById(id);
    }

    @DeleteMapping("/removeAll")
    public String removeAllAccounts() {
        return accountService.removeAll();
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @GetMapping("/user/{userId}")
    public List<Account> getAccountsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return accountService.searchByUser(user);
    }
}

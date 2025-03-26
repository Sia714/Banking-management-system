package com.example.bank.Controller;

import com.example.bank.Model.Account;
import com.example.bank.Model.Transaction;
import com.example.bank.Model.User;
import com.example.bank.Service.AccountService;
import com.example.bank.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/banking_system/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    private AccountService accountService;

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam Double amount) {
        return transactionService.withdraw(accountId, amount);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountId, @RequestParam Double amount) {
        return transactionService.deposit(accountId, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam Double amount) {
        return transactionService.transfer(fromAccountId, toAccountId, amount);
    }


    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getAllTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getAllTransactionsByUser(userId);
    }

    @DeleteMapping("/{id}")
    public String removeTransaction(@PathVariable Long id) {
        return transactionService.removeTransactionById(id);
    }

    @DeleteMapping("/removeAll")
    public String removeAllTransactions() {
        return transactionService.removeAllTransactions();
    }

    @GetMapping("/minAmount/{amount}")
    public List<Transaction> getTransactionsByMinAmount(@PathVariable Double amount) {
        return transactionService.getTransactionsByMinAmount(amount);
    }

    @GetMapping("/time")
    public List<Transaction> getTransactionsByTimeRange(@RequestParam("start") String start, @RequestParam("end") String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return transactionService.getTransactionsByTime(startTime, endTime);
    }
}

package com.example.bank.Service;

import com.example.bank.Model.Account;
import com.example.bank.Model.Transaction;
import com.example.bank.Model.TransactionType;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public String removeTransactionById(Long id) {
        if (!transactionRepository.existsById(id)) {
            return "Transaction not found!";
        }
        transactionRepository.deleteById(id);
        return "Transaction deleted!";
    }

    public String removeAllTransactions() {
        transactionRepository.deleteAll();
        return "All transactions deleted!";
    }

    public List<Transaction> getTransactionsByMinAmount(Double amount) {
        return transactionRepository.findByMinAmount(amount);
    }

    public List<Transaction> getTransactionsByTime(LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByTime(start, end);
    }

    // **Withdraw Money**
    @Transactional
    public String withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account not found!";
        }
        if (account.getBalance() < amount) {
            return "Insufficient funds!";
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return "Withdrawal successful!";
    }

    // **Deposit Money**
    @Transactional
    public String deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account not found!";
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setToAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return "Deposit successful!";
    }

    // **Transfer Money**
    @Transactional
    public String transfer(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);

        if (fromAccount == null || toAccount == null) {
            return "One or both accounts not found!";
        }
        if (fromAccount.getBalance() < amount) {
            return "Insufficient funds!";
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return "Transfer successful!";
    }









}

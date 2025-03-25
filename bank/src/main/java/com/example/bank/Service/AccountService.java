package com.example.bank.Service;

import com.example.bank.Model.Account;
import com.example.bank.Model.User;
import com.example.bank.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository ar;

    public Account saveAccount(Account a) {
        return ar.save(a);
    }

    public Account searchById(Long id) {
        return ar.findById(id).orElse(null);
    }

    public List<Account> getAllAccounts() {
        return ar.findAll();
    }

    public String removeAccountById(Long id) {
        ar.deleteById(id);
        return "Account deleted!!!!";
    }

    public String removeAll() {
        ar.deleteAll();
        return "All Accounts Deleted!!!!";
    }

    @Transactional
    public Account updateAccount(Long id, Account a) {
        Account a1 = searchById(id);
        a1.setAccountNumber(a.getAccountNumber());
        a1.setBalance(a.getBalance());
        a1.setUser(a.getUser());
        return ar.save(a1);
    }

    public List<Account> searchByUser(User user) {
        return ar.findByUser(user);
    }

    public Account searchByAccountNumber(String AccountNumber) {
        return ar.findByAccountNumber(AccountNumber);
    }

    public List<Account> searchByMinBalance(Double balance) {
        return ar.findByMinBalance(balance);
    }


}
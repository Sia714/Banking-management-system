package com.example.bank.Repository;

import com.example.bank.Model.Account;
import com.example.bank.Model.Transaction;
import com.example.bank.Model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
        @Query("SELECT t FROM Transaction t WHERE t.fromAccount.user.id = :userId OR t.toAccount.user.id = :userId")
        List<Transaction> findByUserId(@Param("userId") Long userId);

        @Query("SELECT t FROM Transaction t WHERE t.fromAccount.user.name = :name")
        List<Transaction> findByFromAccount_Name(@Param("name") String name);

        @Query("SELECT t FROM Transaction t WHERE t.toAccount.user.name = :name")
        List<Transaction> findByToAccount_Name(@Param("name") String name);

        List<Transaction> findByTransactionType(TransactionType type);
        List<Transaction> findByFromAccountOrToAccount(Account fromAccount, Account toAccount);

        @Query("SELECT t FROM Transaction t WHERE t.amount >= :amount")
        List<Transaction> findByMinAmount(@Param("amount") Double amount);

        @Query("SELECT t FROM Transaction t WHERE t.timestamp BETWEEN :start AND :end")
        List<Transaction> findByTime(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
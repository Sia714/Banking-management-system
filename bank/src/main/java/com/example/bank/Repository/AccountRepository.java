package com.example.bank.Repository;

import com.example.bank.Model.Account;
import com.example.bank.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);
    Account findByAccountNumber(String accountNum);

    @Query("SELECT a FROM Account a WHERE a.balance >= :balance")
    List<Account> findByMinBalance(@Param("balance") Double balance);

}
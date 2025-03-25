package com.example.bank.Repository;

import com.example.bank.Model.Role;
import com.example.bank.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    User findByEmail(String email);
    List<User> findByRole(Role role);
}
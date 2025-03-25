package com.example.bank.Controller;

import com.example.bank.Model.Account;
import com.example.bank.Model.User;
import com.example.bank.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking_system/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.searchById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable Long id) {
        return userService.removeUserById(id);
    }

    @DeleteMapping("/removeAll")
    public String removeAllUsers() {
        return userService.removeAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getAllAccountsByUser(@PathVariable Long id) {
        return userService.getAllAccountsByUser(id);
    }
}

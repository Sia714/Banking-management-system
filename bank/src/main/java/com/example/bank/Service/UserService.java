package com.example.bank.Service;

import com.example.bank.Model.Account;
import com.example.bank.Model.Role;
import com.example.bank.Model.User;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository ur;
    @Autowired
    AccountRepository ar;
    public User saveUser(User u){ return ur.save(u);}
    public List<Account> getAllAccountsByUser(Long userId) {
        User user = ur.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        return ar.findByUser(user);
    }
    public User searchById(Long id){return ur.findById(id).orElse(null);}
    public List<User> getAllUsers(){
        return ur.findAll();
    }
    public String removeUserById(Long id){
        ur.deleteById(id);
        return "User deleted!!!!";
    }
    public String removeAll(){
        ur.deleteAll();
        return "All Users Deleted!!!!";
    }
    @Transactional
    public User updateUser(Long id, User u) {
        User u1 = searchById(id);
        if (u1 == null) {
            throw new RuntimeException("User not found!");
        }
        u1.setName(u.getName());
        u1.setEmail(u.getEmail());
        u1.setRole(u.getRole());
        return ur.save(u1);
    }
    public List<User> searchByName(String name){
        return ur.findByName(name);
    }
    public User searchByEmail(String email){
        return ur.findByEmail(email);
    }
    public List<User> searchByRole(Role role){
        return ur.findByRole(role);
    }
}

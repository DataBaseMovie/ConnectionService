package com.connexion.services;

import com.connexion.configuration.SpringSessionController;
import com.connexion.entities.User;
import com.connexion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username : " + username);
        } else {
            return user;
        }
    }

    public void addUser(User u) {
        System.out.println("Enregistrement dans la base ");
        userRepository.save(u);

    }

    public void connUser(User u, HttpServletRequest r) {
        User test = userRepository.findByUsername(u.getUsername());
        if (test != null) {
            System.out.println("TEST DE CONNECTION OK");
            SpringSessionController sess = new SpringSessionController();
            sess.persistMessage(test.getUsername(), r);
        }
    }

    public User verifUser(String pseudo, String password) {
        User test = userRepository.findByUsernameAndPassword(pseudo, password);
        return test;
    }

    public User getUser(Long id) {
        System.out.println(id);
        User u = userRepository.findById(id).get();
        return u;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}

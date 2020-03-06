package com.connexion.configuration;

import com.connexion.entities.User;
import com.connexion.enums.RoleEnum;
import com.connexion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    public WebMvcConfig(UserRepository userRepository) {
        // Ceci n'est pas à recopier en production
//        List<RoleEnum> userRole = Collections.singletonList(RoleEnum.USER);
//        List<RoleEnum> adminRole = Arrays.asList(RoleEnum.USER, RoleEnum.ADMINISTRATOR);
//        User user = new User("user", "user", "User", "USER", userRole);
//        User adminUser = new User("admin", "admin", "Admin", "ADMIN", adminRole);
//        userRepository.save(user);
//        userRepository.save(adminUser);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/auth").setViewName("auth");
        registry.addViewController("/auth/admin").setViewName("auth/admin");
    }
}

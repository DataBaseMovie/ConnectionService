package com.connexion.repositories;

import com.connexion.entities.User ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
     User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE LOWER (u.username) = LOWER(:username) and u.password = :password")
     User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "from User u where u.email = ?1")
    Optional<User> findByLogin(String login);
}
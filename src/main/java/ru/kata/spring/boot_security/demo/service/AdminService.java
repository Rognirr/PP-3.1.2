package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    void add(User user);

    User findById(Long id);

    void update(User user);

    void delete(Long id);

    List<User> getAllUsers();

    Optional<User> findByLogin(String login);
}

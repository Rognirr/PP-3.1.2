package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);
}

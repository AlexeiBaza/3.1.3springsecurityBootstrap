package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void create(User user);
    Optional<User> readById(long id);
    List<User> readAll();
    void update(User user);
    void delete (User user);
}

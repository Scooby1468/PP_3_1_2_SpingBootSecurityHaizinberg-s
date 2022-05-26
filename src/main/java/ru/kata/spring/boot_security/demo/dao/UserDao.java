package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.List;

public interface UserDao {

    public MyUser findById(Long id);

    public MyUser findByName(String username);

    public List<MyUser> findAll();

    public MyUser saveUser(MyUser myUser);

    public void deleteById(Long id);

    public MyUser update(MyUser myUser);
}
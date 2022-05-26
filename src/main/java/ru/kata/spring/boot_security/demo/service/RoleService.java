package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    public Role findById(Long id);

    public List<Role> findAll();

    public Role saveRole(Role role);

    public void deleteById(Long id);

    public Role update(Role role);
}
package ru.kata.spring.boot_security.demo.dao;
import ru.kata.spring.boot_security.demo.model.Role;


import java.util.List;

public interface RoleDao {

    public Role findById(Long id);

    public Role findByName(String role);

    public List<Role> findAll();

    public Role saveRole(Role role);

    public void deleteById(Long id);

    public Role update(Role role);
}
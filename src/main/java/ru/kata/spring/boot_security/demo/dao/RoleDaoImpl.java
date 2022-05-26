package ru.kata.spring.boot_security.demo.dao;
import ru.kata.spring.boot_security.demo.model.Role;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

    public RoleDaoImpl() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String role) {
        Query query = entityManager.createQuery("select u from Role u where u.role = :role", Role.class)
                .setParameter("role", role);
        List <Role> roles = query.getResultList();

        return (roles.isEmpty()) ? null: (Role) roles.get(0);

    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public Role saveRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Role u where u.id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public Role update(Role role) {
        final Role updated = entityManager.merge(role);
        entityManager.flush();
        return updated;
    }
}
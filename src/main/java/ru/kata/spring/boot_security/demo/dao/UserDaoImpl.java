package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    public UserDaoImpl() {
    }

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Override
    public MyUser findById(Long id) {
        return entityManager.find(MyUser.class,id);
    }

    @Override
    public MyUser findByName(String username) {
        return entityManager.createQuery("select u from MyUser u where u.username = :username", MyUser.class)
                .setParameter("username", username).getSingleResult();
    }


    @Override
    public List<MyUser> findAll() {
        return entityManager.createQuery("select u from MyUser u", MyUser.class).getResultList();
    }

    @Override
    public MyUser saveUser(MyUser myUser) {
        entityManager.persist(myUser);
        return myUser;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from MyUser u where u.id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public MyUser update(MyUser myUser) {
        final MyUser updated = entityManager.merge(myUser);
        entityManager.flush();
        return updated;
    }
}
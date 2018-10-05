package com.rogern.controller;

import com.rogern.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;
import com.rogern.model.User;

public class DBController {
    private Session session;

    public DBController() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public List getUsers() {
        List<?> users = session.createNamedQuery("FROM User").getResultList();
        return users;
    }

    public void listUsers() {
        List<?> users = getUsers();
        users.forEach(l -> System.out.println(l.toString()));
    }

    public User findUserWithUsername(String username) {
        List<?> users = session.createQuery("From Users WHERE username = '" + username + "'").getResultList();
        if (users.size() == 1) {
            return (User) users.get(0);
        } else {
            throw new RuntimeException("More than one user with <username> in list");
        }
    }

    public void updateUser(Integer id, User user) {
        User u = session.find(User.class, id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        session.update(u);
        commit();
    }

    public void deleteUser(Integer id) {
        if(!session.getTransaction().isActive()){
            session.beginTransaction();
        }
        session.beginTransaction();
        User u = session.find(User.class, id);
        session.delete(u);
        commit();
    }

    public void saveUser(User user) {
        if(!session.getTransaction().isActive()){
            session.beginTransaction();
        }
        session.save(user);
        commit();
    }

    public void saveUsers(User...users) {
        Arrays.asList(users).forEach(this::saveUser);
    }

    private void commit() {
        session.getTransaction().commit();
    }

    public void close(){
        session.getSessionFactory().close();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

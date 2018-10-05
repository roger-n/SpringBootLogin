package com.rogern.controller;

import com.rogern.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class DBController {
    private Session session;

    public DBController() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

//Getter/helper methods

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

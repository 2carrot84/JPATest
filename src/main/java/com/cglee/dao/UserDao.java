package com.cglee.dao;

import com.cglee.model.UsersEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eguns on 2017. 3. 12..
 */
public class UserDao {
    public void add(UsersEntity user) {
        Transaction trns = null;
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            trns = session.beginTransaction();
            session.save(user);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int getCount() {
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        List listResult = session.createQuery("select count(*) from UsersEntity").list();
        Number number = (Number) listResult.get(0);

        return number.intValue();
    }

    public void deleteUser(String id) {
        Transaction trns = null;
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            trns = session.beginTransaction();
            UsersEntity user = (UsersEntity) session.load(UsersEntity.class, id);
            session.delete(user);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAll() {
        Transaction trns = null;
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            trns = session.beginTransaction();
            session.createQuery("delete from UsersEntity").executeUpdate();
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {

            session.close();
        }
    }

    public void updateUser(UsersEntity user) {
        Transaction trns = null;
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            trns = session.beginTransaction();
            session.update(user);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<UsersEntity> getAllUsers() {
        List<UsersEntity> users = new ArrayList<UsersEntity>();
        Transaction trns = null;
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            trns = session.beginTransaction();
            users = session.createQuery("from UsersEntity ").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    public UsersEntity getUserById(String userid) {
        UsersEntity user = null;
        SessionFactory sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String queryString = "from UsersEntity where id = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", userid);
            user = (UsersEntity) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}

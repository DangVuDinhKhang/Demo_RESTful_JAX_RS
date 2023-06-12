package com.appsdeveloperblog.app.ws.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        Configuration conf = new Configuration();
        conf.configure();

        try {
//            InputStream in = getResourceAsStream("/hibernate.cfg.xml");
//            sessionFactory = new Configuration().addInputStream(in).configure().buildSessionFactory();
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            System.err.println("!Error: Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

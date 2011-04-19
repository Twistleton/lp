package com.rolfbenz.lp.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: U987
 * Date: 05.04.11
 *
 */
public class JpaUtil {

    private static final EntityManagerFactory emf;

    static {

        try {

            emf = Persistence.createEntityManagerFactory("punit");
        } catch (Throwable ex) {

            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {

        return emf;

    }

}

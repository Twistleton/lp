package com.rolfbenz.lp.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * lp - JpaUtil - get entity manager
 *
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

            Logger.getAnonymousLogger().log(Level.SEVERE , "Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {

        return emf;

    }

}

package org.levelp.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UsersDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private UsersDAO usersDAO;
    private final String user = "testuser1";
    private final String password = "password1";

    @Before
    public void configure() {
        factory = Persistence.createEntityManagerFactory(
                "TestPersistenceUnit"
        );
        manager = factory.createEntityManager();
        usersDAO = new UsersDAO(manager);

        User newUser = new User(user, password, false);
        manager.getTransaction().begin();
        manager.persist(newUser);
        manager.getTransaction().commit();
    }

    @After
    public void cleanup() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void findByLogin() {
        assertNull(usersDAO.findByLogin("non existing user"));

        User found = usersDAO.findByLogin(user);
        assertNotNull(found);
        assertEquals(user, found.getLogin());
    }

    @Test
    public void findByLoginAndPassword() {
        assertNull(usersDAO.findByLoginAndPassword(user, ""));
        assertNull(usersDAO.findByLoginAndPassword(user, "ppp"));
        assertNull(usersDAO.findByLoginAndPassword(user, "pass1"));
        assertNull(usersDAO.findByLoginAndPassword("user", password));

        User found = usersDAO.findByLoginAndPassword(user, password);
        assertNotNull(found);
        assertEquals(user, found.getLogin());
        assertEquals(password, found.getPassword());
    }


    @Test //todo after fix
    public void findByIsAdmin() {
        assertEquals(user, usersDAO.findByIsAdmin(false).get(0).getLogin());
        assertTrue(usersDAO.findByIsAdmin(true).isEmpty());
    }
}

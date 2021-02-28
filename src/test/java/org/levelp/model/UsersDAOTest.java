package org.levelp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UsersDAOTest {
    private EntityManagerFactory factory;
    @Autowired
    private EntityManager manager;
    private UsersDAO usersDAO;
    private final String userNoAdmin = "testuser1";
    private final String password = "password1";
    private final String email = "email1";
    private final String userAdmin = "testuser2";
    private final String passwordAdmin = "password2";
    private final String emailAdmin = "email2";

    @Before
    public void configure() {
        User newUser = new User(userNoAdmin, password, email, false);
        User newUser1 = new User(userAdmin, passwordAdmin, emailAdmin, true);
        manager.getTransaction().begin();
        manager.persist(newUser);
        manager.persist(newUser1);
        manager.getTransaction().commit();

    }

    @Test
    public void findByLogin() {
        assertNull(usersDAO.findByLogin("non existing user"));
        assertNull(usersDAO.findByLogin(""));

        User found = usersDAO.findByLogin(userNoAdmin);
        assertNotNull(found);
        assertEquals(userNoAdmin, found.getLogin());
    }

    @Test
    public void findByLoginAndPassword() {
        assertNull(usersDAO.findByLoginAndPassword(userNoAdmin, ""));
        assertNull(usersDAO.findByLoginAndPassword(userNoAdmin, "ppp"));
        assertNull(usersDAO.findByLoginAndPassword(userNoAdmin, passwordAdmin));
        assertNull(usersDAO.findByLoginAndPassword("user", password));

        User found = usersDAO.findByLoginAndPassword(userNoAdmin, password);
        assertNotNull(found);
        assertEquals(userNoAdmin, found.getLogin());
        assertEquals(password, found.getPassword());
    }


    @Test
    public void findByIsAdmin() {
        assertEquals(userNoAdmin, usersDAO.findByIsAdmin(false).get(0).getLogin());
        assertEquals(userAdmin, usersDAO.findByIsAdmin(true).get(0).getLogin());
    }

    @Test
    public void findAllUsers() {
        assertFalse(usersDAO.findAllUsers().isEmpty());
        assertEquals(2, usersDAO.findAllUsers().size());
        assertTrue(usersDAO.findAllUsers().get(0).getLogin().equals(userNoAdmin));
        assertTrue(usersDAO.findAllUsers().get(1).getLogin().equals(userAdmin));
    }
}

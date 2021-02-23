package ru.levelup;

import org.junit.Assert;
import org.junit.Test;
import org.levelp.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Test
    public void smokeTest() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            User user = new User("testuser1", "password1", true);
            manager.persist(user);

            User found = manager.find(User.class, user.getId());
            Assert.assertNotNull(found);

            manager.getTransaction().commit();

            manager.refresh(found);

            manager.getTransaction().begin();
            manager.remove(found);
            manager.getTransaction().commit();
        } finally {
            manager.close();
            factory.close();
        }
    }
}

package ru.levelup;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.levelp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class EntityManagerTest {
    @Autowired
    private EntityManager manager;

    @Test
    public void smokeTest() {
        manager.getTransaction().begin();
        User user = new User("testuser1", "password1", "ee@mail.com", true);
        manager.persist(user);

        User found = manager.find(User.class, user.getId());
        Assert.assertNotNull(found);

        manager.getTransaction().commit();

        manager.refresh(found);

        manager.getTransaction().begin();
        manager.remove(found);
        manager.getTransaction().commit();

}
}

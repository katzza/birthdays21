package org.levelp.model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class BirthadayChildsDAO {
    private EntityManager manager;

    public BirthadayChildsDAO(EntityManager manager) {
        this.manager = manager;
    }

    public List<BirthdayChild> findByTeam(Team team) {
        return manager.createNamedQuery("findByTeam", BirthdayChild.class)  //annotation in class BirthdayChild
                .setParameter("team", team)
                .getResultList();
    }

    public List<BirthdayChild> sortByBirthday() {
        return manager.createNamedQuery("sortByBirthday", BirthdayChild.class)  //annotation in class BirthdayChild
                .getResultList();
    }

    public List<BirthdayChild> getAllBirthdayChildren() {
        return manager.createNamedQuery("getAllBirthdayChildren", BirthdayChild.class)  //annotation in class BirthdayChild
                .getResultList();
    }

}

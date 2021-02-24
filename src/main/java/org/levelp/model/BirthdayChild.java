package org.levelp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BirthdayChildren")
@NamedQueries({
        @NamedQuery(name = "findByTeam", query = "from BirthdayChild where team = :team order by lastName asc"), //BirthdayChildsDAO, method findByTeam
        @NamedQuery(name = "sortByBirthday", query = "from BirthdayChild order by birthday asc"),
        @NamedQuery(name = "getAllBirthdayChildren", query = "from BirthdayChild order by lastName asc"),
})
public class BirthdayChild {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Convert(converter = TeamConverter.class)
    private Team team = Team.FAIRY;

    @Column(nullable = false)
    private boolean isKindFairy = false;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    public BirthdayChild() {
    }

    public BirthdayChild(String name, String lastName, Team team, Date birthday) {
        this.name = name;
        this.lastName = lastName;
        this.team = team;
        this.birthday = birthday;
    }

    public BirthdayChild(String name, String lastName, Team team, Date birthday, boolean isKindFairy) {
        this.name = name;
        this.lastName = lastName;
        this.team = team;
        this.isKindFairy = isKindFairy;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isKindFairy() {
        return isKindFairy;
    }

    public void setIsKindFairy(boolean isKindFairy) {
        this.isKindFairy = isKindFairy;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) { //todo fix it with converter
        this.team = team;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) { //todo fix it with converter
        this.birthday = birthday;
    }

}

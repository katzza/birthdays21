package org.levelp.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "findByIsAdmin", query = "from User where isAdmin = :isAdmin"), //UsersDAO, method findByIsAdmin
        @NamedQuery(name = "findAllUsers", query = "from User order by login asc"),
})
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, length = 30)
    private String login;

    @Column(nullable = false, length = 15)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(length = 50)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private BirthdayChild birthdaychild;

    @Transient //@Transient аннотация JPA используется для обозначения того, что поле не должно сохраняться в базе данных
    private String notForDb;

    public User() {
    }

    public User(String login, String password, String email, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
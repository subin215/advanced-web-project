package assets;


import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.*;

/**
 * Created by subin on 2/7/17.
 */
@Entity
@Table(name="User")
public class User {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Long Id;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;


    public User(){}

    public User(Long id, String userName, String password) {
        Id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Transactional
    public static User authenticate(String userName, String password) {
        return JPA.em().createQuery("FROM User", User.class).getSingleResult();

//        return find.where().eq("userName", userName)
//                .eq("password", password).findUnique();
    }
}

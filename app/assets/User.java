package assets;


import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
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
    @Constraints.Required
    private String userName;

    @Column(name="PASSWORD")
    @Constraints.Required
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
        User user = JPA.em().createQuery("FROM User u WHERE u.userName = :setName", User.class)
                .setParameter("setName", userName)
                .getSingleResult();

        //Check Password.
        if(BCrypt.checkpw(password, user.getPassword())){
            return user;
        } else{
            return null;
        }
    }

    @Transactional
    public static void registerUser(User user){
        // Encrypt Password before saving
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        JPA.em().persist(user);
    }

}

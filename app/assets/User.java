package assets;


import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model.Finder;

/**
 * Created by subin on 2/7/17.
 */
@Entity
public class User {

    @Id
    private Long Id;

    private String userName;

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

    private static Finder<String,User> find = new Finder<String,User>(
            String.class, User.class
    );

    public static User authenticate(String email, String password) {
        return find.where().eq("email", email)
                .eq("password", password).findUnique();
    }
}

package assets;


import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by Subin Sapkota on 2/7/17.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Id != null ? !Id.equals(user.Id) : user.Id != null) return false;
        if (!userName.equals(user.userName)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}

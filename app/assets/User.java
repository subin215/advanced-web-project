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

    @Column(name="USERNAME", unique = true)
    @Constraints.Required
    @Constraints.MinLength(1)
    @Constraints.MaxLength(40)
    private String userName;

    @Column(name="PASSWORD")
    @Constraints.Required
    @Constraints.MinLength(value = 8)
    @Constraints.MaxLength(100)
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (!Id.equals(user.Id)) {
      return false;
    }
    if (!userName.equals(user.userName)) {
      return false;
    }
    return password.equals(user.password);
  }

  @Override
  public int hashCode() {
    int result = Id.hashCode();
    result = 31 * result + userName.hashCode();
    result = 31 * result + password.hashCode();
    return result;
  }
}

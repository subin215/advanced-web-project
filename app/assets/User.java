package assets;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints;

/**
 * Created by Subin Sapkota on 2/7/17.
 */
@Entity
@Table(name = "User")
public class User {

  @Id
  @Column(name = "ID")
  @GeneratedValue
  private Long id;

  @Column(name = "USERNAME", unique = true)
  @Constraints.Required
  @Constraints.MinLength(1)
  @Constraints.MaxLength(40)
  private String userName;

  @Column(name = "PASSWORD")
  @Constraints.Required
  @Constraints.MinLength(value = 8)
  @Constraints.MaxLength(100)
  private String password;


  public User() {
  }

  public User(Long id, String userName, String password) {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}

package services.spi;

import assets.User;
import java.util.List;

/**
 * Created by Subin Sapkota on 2/13/17.
 */
public interface UserService {

  /**
   * Authenticate user by cross checking with DB.
   *
   * @param user
   * @return
   */
  User authenticate(User user);

  /**
   * Register User.
   * @param user
   */
  boolean registerNewUser(User user);

  /**
   * Get User for given userName.
   *
   * @param userName
   * @return
   */
  List<User> getUserForName(String userName);

  /**
   * Persist provided user to DB
   * @param user
   */
  User saveUser(User user);
}

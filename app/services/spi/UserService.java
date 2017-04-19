package services.spi;

import assets.User;
import java.util.List;

/**
 * Created by Subin Sapkota on 2/13/17.
 */
public interface UserService {

  /**
   * Authenticate a user based on provided User object.
   * @param user: assets.User object
   * @return assets.User
   */
  User authenticate(User user);

  /**
   * Register new user to database.
   * @param user: assets.User object
   * @return true - successfully registered OR false - registration failed.
   */
  boolean registerNewUser(User user);

  /**
   * Get user for provided username.
   * @param userName - string username to get from DB
   * @return java List of users matching username.
   */
  List<User> getUserForName(String userName);

}

package services.spi;

import assets.User;
import java.util.List;

/**
 * Created by Subin Sapkota on 2/13/17.
 */
public interface UserService {

  /**
   * Authenticate user by cross checking with DB.
   */
  User authenticate(User user);

  /**
   * Register User.
   */
  boolean registerNewUser(User user);

  /**
   * Get User for given userName.
   */
  List<User> getUserForName(String userName);

}

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
     * Persist new user to database.
     * @param user
     */
    Boolean registerNewUser(User user); //Boolean - if you expect it to be null.

    /**
     * Get User for given userName.
     *
     * @param userName
     * @return
     */
    List<User> getUserForName(String userName);

}

package services.spi;

import assets.User;

/**
 * Created by Subin Sapkota on 2/13/17.
 */
public interface IUserService {

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
    void registerUser(User user);

<<<<<<< HEAD
    /**
     * Get User for given userName.
     *
     * @param userName
     * @return
     */
    User getUserForName(String userName);

    /**
     * Logout user session.
     */
    void logout();
=======
>>>>>>> parent of ad189b2... Changed method of catching username duplicates during registration.
}

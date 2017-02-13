package services;

import assets.User;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import services.spi.IUserService;

/**
 * Created by Subin Sapkota on 2/12/17.
 */
public class UserService implements IUserService{

    /**
     * Implementation of authentication. Check password has to verify password.
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User authenticate(User user) {
        User userFromDB = JPA.em().createQuery("FROM User u WHERE u.userName = :setName", User.class)
                .setParameter("setName", user.getUserName())
                .getSingleResult();

        //Check Password hash
        if(BCrypt.checkpw(user.getPassword(), userFromDB.getPassword())){
            return user;
        } else{
            return null;
        }
    }

    /**
     * Implementation of register User.
     * @param user
     */
    @Override
    @Transactional
    public void registerUser(User user) {
        // Encrypt Password before saving
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        JPA.em().persist(user);
    }


}

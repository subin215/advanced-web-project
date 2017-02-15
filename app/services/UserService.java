package services;

import assets.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import services.spi.IUserService;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * Created by Subin Sapkota on 2/12/17.
 */
public class UserService implements IUserService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
            logger.info("USER: {} is authenticated.", user.getUserName());
            return user;
        } else{
            return null;
        }
    }

    /**
     * Implementation of register User.
     * Will update user password if it already exists in DB.
     *
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

    /**
     * Implementation of get User for given userName.
     *
     * @param userName
     * @return
     */
    @Override
    public User getUserForName(String userName) {
        return JPA.em().createQuery("FROM User u WHERE u.userName = :setName", User.class)
                .setParameter("setName", userName)
                .getSingleResult();
    }


}

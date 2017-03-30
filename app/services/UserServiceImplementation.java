package services;

import assets.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.spi.UserService;

/**
 * Created by Subin Sapkota on 2/12/17.
 */
@Service
public class UserServiceImplementation implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
  private int minUserNameLength;
  private int maxUserNameLength;
  private int minPasswordLength;
  private int maxPasswordLength;

  @PersistenceContext
  private EntityManager em;

  @Autowired
  public UserServiceImplementation(Integer minUserNameLength, Integer maxUserNameLength,
      Integer minPasswordLength, Integer maxPasswordLength) {
    this.minUserNameLength = minUserNameLength;
    this.maxUserNameLength = maxUserNameLength;
    this.minPasswordLength = minPasswordLength;
    this.maxPasswordLength = maxPasswordLength;
  }

  /**
   * Implementation of authentication. Check password has to verify password.
   */
  @Override
  public User authenticate(User user) {
    if (user == null) {
      return null;
    }
    if (user.getUserName() == null || user.getPassword() == null) {
      return null;
    }
    if (user.getUserName().length() < minUserNameLength
        || user.getUserName().length() > maxUserNameLength) {
      return null;
    }
    if (user.getPassword().length() < minPasswordLength
        || user.getPassword().length() > maxPasswordLength) {
      return null;
    }
    // Get user from DB.
    List<User> userFromDB;
    userFromDB = getUserForName(user.getUserName());
    if (userFromDB.size() == 0) {
      return null;
    }
    //Check Password hash
    if (!BCrypt.checkpw(user.getPassword(), userFromDB.get(0).getPassword())) {
      return null;
    }
    logger.info("USER: {} is authenticated.", user.getUserName());
    return user;
  }

  /**
   * Implementation of register User.
   */
  @Override
  @Transactional
  public boolean registerNewUser(User user) {
    // Check for null user object.
    if (user == null) {
      return false;
    }
    // Check for null fields in user object.
    if (user.getUserName() == null || user.getPassword() == null) {
      logger.warn("UserName or Password field was left empty.");
      return false;
    }
    if (user.getUserName().length() < minUserNameLength
        || user.getUserName().length() > maxUserNameLength) {
      return false;
    }
    if (user.getPassword().length() < minPasswordLength
        || user.getPassword().length() > maxPasswordLength) {
      return false;
    }
    // Check if user exists in database.
    if (getUserForName(user.getUserName()).size() != 0) {
      logger.warn(user.getUserName() + " already in database. Returned false");
      return false;
    }
    // Encrypt Password before saving
    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(hashed);
    saveUser(user);
    return true;
  }

  /**
   * Implementation of get User for given userName.
   */
  @Override
  public List<User> getUserForName(String userName) {
    return em.createQuery("FROM User u WHERE u.userName = :setName", User.class)
        .setParameter("setName", userName)
        .getResultList();
  }

  /**
   * Persist user to the database.
   */
  private User saveUser(User user) {
    if (user.getPassword() == null || user.getUserName() == null) {
      return user;
    }
    if (user.getUserName().length() < minUserNameLength
        || user.getUserName().length() > maxUserNameLength) {
      return user;
    }
    if (user.getPassword().length() < minPasswordLength
        || user.getPassword().length() > maxPasswordLength) {
      return user;
    }
    em.persist(user);
    return user;
  }
}

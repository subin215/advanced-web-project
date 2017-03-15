package services;

import assets.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import services.spi.UserService;

/**
 * Created by Subin Sapkota on 2/12/17.
 */
@Service
public class UserServiceImplementation implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

  @PersistenceContext
  private EntityManager em;

  // TODO: Protection in depth, i.e, validation check in service too.
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
    if (user.getUserName() == null || user.getPassword() == null) {
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
   * Implementation of saveUser.
   */
  private User saveUser(User user) {
    if (user.getPassword() == null || user.getUserName() == null) {
      return user;
    }
    em.persist(user);
    return user;
  }
}

package services;

import assets.User;
import conf.TestDataConfig;
import configs.AppConfig;
import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import services.spi.UserService;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by Subin Sapkota on 2/17/17.
 */
@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class UserServiceImplementationTest extends AbstractTransactionalJUnit4SpringContextTests{

  @Inject
  private UserService userService;

  @Test
  public void testSaveUser(){
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("iAmBatman");
    user = userService.saveUser(user);
    Assert.assertTrue("User saved successfully", user.getId() != null);
  }

  @Test
  public void testEmptyUserSave(){
    User user = new User();
    user = userService.saveUser(user);
    Assert.assertTrue("Empty user save didn't persist user", user.getId() == null);
  }

  @Test
  public void testEmptyField(){
    // Empty userName
    User user = new User();
    user.setPassword("iAmBatman");
    user = userService.saveUser(user);
    Assert.assertTrue("User with empty userName didn't save", user.getId() == null);

    // Empty password
    user.setUserName("bruceWayne");
    user.setPassword(null);
    user = userService.saveUser(user);
    Assert.assertTrue("User with empty password didn't save", user.getId() == null);
  }

  @Test
  public void testGetUserForName(){
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("iAmBatman");
    user = userService.saveUser(user);
    Assert.assertTrue("Obtained user for username successfully",
        userService.getUserForName(user.getUserName()).size() > 0);

  }

  @Test
  public void testGetUserNameForNullUserName(){
    List<User> user = userService.getUserForName(null);
    Assert.assertTrue("User empty didn't receive a result", user.size() == 0);
  }

  @Test
  public void testRegisterUser(){
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("iAmPassword");
    Assert.assertTrue("User registered successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterEmptyUser(){
    User user = new User();
    user.setUserName("bruceWayne");
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testAuthenticateExistingUser(){
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("iAmBatman");
    userService.registerNewUser(user);

    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("bruceWayne");
    userToAuthenticate.setPassword("iAmBatman");
    userToAuthenticate = userService.authenticate(userToAuthenticate);
    Assert.assertTrue("User authenticated successfully", userToAuthenticate != null);
  }

  @Test
  public void testAuthenticateEmptyUser(){
    Assert.assertTrue("User returned is null", userService.authenticate(null) == null);
  }

  @Test
  public void testAuthenticateBadPassword(){
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("iAmBatman");
    userService.registerNewUser(user);

    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("bruceWayne");
    userToAuthenticate.setPassword("iAmIronMan");
    userToAuthenticate = userService.authenticate(userToAuthenticate);
    Assert.assertTrue("User returned null, not authenticated successfully",
        userToAuthenticate == null);
  }
}

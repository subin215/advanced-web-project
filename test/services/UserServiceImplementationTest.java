package services;

import assets.User;
import conf.TestDataConfig;
import configs.AppConfig;
import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import services.spi.UserService;

/**
 * Created by Subin Sapkota on 2/17/17.
 */
@ContextConfiguration(classes = {AppConfig.class, TestDataConfig.class})
public class UserServiceImplementationTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Inject
  private UserService userService;

  @Before
  public void beforeTest() {
    User user = new User();
    user.setUserName("allTheseSquares");
    user.setPassword("makeACircle");
    userService.registerNewUser(user);
  }

  /*
   * getUserForName(String) method test
   */
  @Test
  public void testGetUserForName() {
    Assert.assertTrue("Obtained user for username successfully",
        userService.getUserForName("allTheseSquares").size() > 0);

  }

  @Test
  public void testGetUserNameForNonExistingUserName() {
    List<User> user = userService.getUserForName("bob");
    Assert.assertTrue("User empty didn't receive a result", user.size() == 0);
  }

  @Test
  public void testGetUserNameForNullUserName() {
    List<User> user = userService.getUserForName(null);
    Assert.assertTrue("User empty didn't receive a result", user.size() == 0);
  }

  /*
   * registerUser(User) test
   */
  @Test
  public void testRegisterUser() {
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("iAmPassword");
    Assert.assertTrue("User registered successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterExistingUserName() {
    User user = new User();
    user.setUserName("allTheseSquares");
    user.setPassword("makeACircle");
    Assert.assertFalse("User didn't regiseter successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterEmptyUser() {
    User user = new User();
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterNullUsernameUser() {
    User user = new User();
    user.setUserName(null);
    user.setPassword("iAmPassword");
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterNullPasswordUser() {
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword(null);
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterBelowMinLengthPasswordUser() {
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("jtjiskt");
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterAboveMaxLengthPasswordUser() {
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("g7KeS6vGxkxCCABmOYkNvDhOuj0IHTAGtAKMD51fzgH3Cm2gfegtIULLjGPghCZuR5TO8m995qPx9MyPfOUwtHwB7ShbK33ulDEJY");
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterBelowMinLengthUserNameUser() {
    User user = new User();
    user.setUserName("");
    user.setPassword("iAmPassword");
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterAboveMaxLengthUserNameUser() {
    User user = new User();
    user.setUserName("sUlmu848GPXSOE7oZFO6atm2Vhf5pM7xIlVeur6Rj");
    user.setPassword("iAmPassword");
    Assert.assertFalse("User didn't register successfully", userService.registerNewUser(user));
  }

  @Test
  public void testRegisterMaxLengthFieldsUser() {
    User user = new User();
    StringBuilder longString = new StringBuilder();
    for(int i = 0; i < 40; i++){
      longString.append("j");
    }
    user.setUserName(longString.toString());
    user.setPassword(longString.toString());
    Assert.assertTrue("User registered successfully", userService.registerNewUser(user));
  }

  /*
   * authenticate(User) method tests
   */
  @Test
  public void testAuthenticateExistingUser() {
    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("allTheseSquares");
    userToAuthenticate.setPassword("makeACircle");
    userToAuthenticate = userService.authenticate(userToAuthenticate);
    Assert.assertTrue("User authenticated successfully", userToAuthenticate != null);
  }

  @Test
  public void testAuthenticateEmptyUser() {
    Assert.assertTrue("User returned is null", userService.authenticate(null) == null);
  }

  @Test
  public void testAuthenticateBadPassword() {
    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("allTheseSquares");
    userToAuthenticate.setPassword("dontMakeACircle");
    userToAuthenticate = userService.authenticate(userToAuthenticate);
    Assert.assertTrue("User returned null, not authenticated successfully",
        userToAuthenticate == null);
  }

  @Test
  public void testAuthenticateEmptyStringUserName(){
    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("");
    userToAuthenticate.setPassword("dontMakeACircle");
    Assert.assertTrue("User returned is null, handled empty userName", userService.authenticate(userToAuthenticate) == null);
  }

  @Test
  public void testAuthenticateNullUserName(){
    User userToAuthenticate = new User();
    userToAuthenticate.setUserName(null);
    userToAuthenticate.setPassword("dontMakeACircle");
    Assert.assertTrue("User returned is null, handled exception", userService.authenticate(userToAuthenticate) == null);
  }

  @Test
  public void testAuthenticateNullPassword(){
    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("allTheseSquares");
    userToAuthenticate.setPassword(null);
    Assert.assertTrue("User returned is null, handled exception", userService.authenticate(userToAuthenticate) == null);
  }

  @Test
  public void testAuthenticateNullPasswordForNonExistingUser(){
    User userToAuthenticate = new User();
    userToAuthenticate.setUserName("allTheseTriangles");
    userToAuthenticate.setPassword(null);
    Assert.assertTrue("User returned is null, handled exception", userService.authenticate(userToAuthenticate) == null);
  }

  @Test
  public void testAuthenticateBelowMinLengthPasswordUser() {
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("jtjiskt");
    Assert.assertTrue("User didn't authenticate successfully", userService.authenticate(user) == null);
  }

  @Test
  public void testAuthenticateAboveMaxLengthPasswordUser() {
    User user = new User();
    user.setUserName("bruceWayne");
    user.setPassword("g7KeS6vGxkxCCABmOYkNvDhOuj0IHTAGtAKMD51fzgH3Cm2gfegtIULLjGPghCZuR5TO8m995qPx9MyPfOUwtHwB7ShbK33ulDEJY");
    Assert.assertTrue("User didn't authenticate successfully", userService.authenticate(user) == null);
  }

  @Test
  public void testAuthenticateBelowMinLengthUserNameUser() {
    User user = new User();
    user.setUserName("");
    user.setPassword("iAmPassword");
    Assert.assertTrue("User didn't authenticate successfully", userService.authenticate(user) == null);
  }

  @Test
  public void testAuthenticateAboveMaxLengthUserNameUser() {
    User user = new User();
    user.setUserName("sUlmu848GPXSOE7oZFO6atm2Vhf5pM7xIlVeur6Rj");
    user.setPassword("iAmPassword");
    Assert.assertTrue("User didn't authenticate successfully", userService.authenticate(user) == null);
  }

}

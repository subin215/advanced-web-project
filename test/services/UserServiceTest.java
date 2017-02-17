package services;

import assets.User;
import conf.TestDataConfig;
import configs.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 * Created by Subin Sapkota on 2/17/17.
 */
@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class UserServiceTest {

    @Inject
    private UserService userService;

    @Test
    public void testRunnable(){}

    @Test
    public void testEmptyUser(){
        User user = new User();
        Assert.assertFalse(userService.registerNewUser(user));
    }
}

package assetsTest;

import assets.User;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import org.junit.*;
import static org.fest.assertions.Assertions.*;

import static org.mindrot.jbcrypt.BCrypt.gensalt;

/**
 * Created by subin on 2/12/17.
 */
public class UserTests {

    @Test
    public void testRunnable(){

    }

    @Test
    @Transactional
    public void testSave(){
        User user = new User();

        String hashedPassword = BCrypt.hashpw("password", gensalt());
        String userName = "ssapkota";
        user.setPassword(hashedPassword);
        user.setUserName(userName);

        JPA.em().persist(user);
        assertThat(userName).isEqualToIgnoringCase("ssapkota");
    }
}



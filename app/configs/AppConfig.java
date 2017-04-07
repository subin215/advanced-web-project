package configs;

/**
 * Created by Subin Sapkota on 2/15/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/*
 * Configuration class.
 */
@Configuration
/*
 * Component scan will check in packages.
 */
@ComponentScan({
    "controllers", "services"
})
@PropertySource("classpath:db.properties")
public class AppConfig {

  @Autowired
  Environment env;

  // Beans for Validation of User Table columns.
  @Bean
  public Integer minUserNameLength(){
    String value = env.getProperty("user.userName.minLength");
    return Integer.parseInt(value);
  }

  @Bean
  public Integer maxUserNameLength(){
    String value = env.getProperty("user.userName.maxLength");
    return Integer.parseInt(value);
  }

  @Bean
  public Integer minPasswordLength(){
    String value = env.getProperty("user.password.minLength");
    return Integer.parseInt(value);
  }

  @Bean
  public Integer maxPasswordLength(){
    String value = env.getProperty("user.password.maxLength");
    return Integer.parseInt(value);
  }
}
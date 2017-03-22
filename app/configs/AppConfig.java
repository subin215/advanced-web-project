package configs;

/**
 * Created by Subin Sapkota on 2/15/17.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
public class AppConfig {

}
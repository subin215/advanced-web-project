package configs;

/**
 * Created by Subin Sapkota on 2/15/17.
 */

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import play.db.DB;

@Configuration
@EnableTransactionManagement
public class DataConfig {

  /**
   * Entity manager is deferred to spring context management.
   */
  @Bean
  public EntityManagerFactory entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(true); // Show SQL Statements in logs?
    vendorAdapter.setGenerateDdl(false); // Generate tables if they don't exist in DB?
    vendorAdapter.setDatabase(Database.MYSQL);

    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setPackagesToScan("assets"); // Look for Object to map to DB tables in this package.
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    entityManagerFactory.setDataSource(dataSource());
    entityManagerFactory.afterPropertiesSet();
    return entityManagerFactory.getObject();
  }

  /**
   *
   * @return
   */
  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory());
  }

  /**
   * Get datasource from play framework configurations.
   */
  @Bean
  public DataSource dataSource() {
    // Return the datasource from the play framework.
    return DB.getDataSource();
  }
}
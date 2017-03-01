package controllers;

import conf.TestDataConfig;
import configs.AppConfig;
import javax.inject.Inject;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Created by Subin Sapkota on 2/26/17.
 */
@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class ApplicationTest extends AbstractTransactionalJUnit4SpringContextTests{

  @Test
  public void testRunnable(){}

}

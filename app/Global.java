/**
 * Created by Subin Sapkota on 2/15/17.
 */

import configs.AppConfig;
import configs.DataConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;

/*
 * Play uses GlobalSettings on startup.
 */
public class Global extends GlobalSettings {

  private ApplicationContext ctx;

  /**
   * On start opt to use Annotations to configure application context.
   */
  @Override
  public void onStart(Application app) {
    super.onStart(app);
    ctx = new AnnotationConfigApplicationContext(AppConfig.class, DataConfig.class);
  }

  /**
   * Return an instance of a class whenever the class is called.
   */
  @Override
  public <A> A getControllerInstance(Class<A> clazz) {
    return ctx.getBean(clazz);
  }
}

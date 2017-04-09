package controllers;

import static play.data.Form.form;

import assets.CurrencyConvert;
import assets.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.spi.ExchangeService;
import services.spi.UserService;
import views.html.convertCurr;
import views.html.home;
import views.html.index;
import views.html.register;

/**
 * Created by Subin Sapkota on 2/7/17.
 */
@Component
public class Application extends Controller {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  private final UserService userService;
  private final ExchangeService exchangeService;

  @Autowired
  public Application(UserService userService, ExchangeService exchangeService) {
    this.userService = userService;
    this.exchangeService = exchangeService;
  }

  /**
   * Render Index page
   */
  public Result index() {
    // Check existing session. If session ont found, redirect to convert currency page.
    if(session().get("username") != null) {
      logger.info("Session already exists. Redirected to currency exchange page.");
      convertCurr();
    }
    Form<User> loginForm = form(User.class);
    logger.info("Rendered Login page");
    return ok(
        index.render(loginForm)
    );
  }

  /**
   * Render home page if session exists.
   */
  public Result home() {
    // Check existing session. If session ont found, redirect to convert currency page.
    if (session().get("username") == null) {
      logger.info("No session found. Redirected to homepage.");
      return logout();

    }
    logger.info("Rendered Home page");
    return ok(
        home.render()
    );
  }

  /**
   * Render user sign up page.
   */
  public Result register() {
    // Check existing session. If session ont found, redirect to convert currency page.
    if(session().get("username") != null) {
      logger.info("Session exists. Redirected to convert currency page.");
      convertCurr();
    }
    Form<User> registrationForm = form(User.class);
    logger.info("Rendered Registration page");
    return ok(
        register.render(registrationForm)
    );
  }

  /**
   * Render convert currency page.
   * @return
   */
  public Result convertCurr() {
    // Check session. If session doesn't exist then redirect to login page.
    if (session().get("username") == null) {
      logger.info("No session found. Redirected to login page.");
      return logout();

    }
    logger.info("Rendered convert currency page.");
    Form<CurrencyConvert> currencyConvertForm = form(CurrencyConvert.class);
    return ok(
        convertCurr.render(currencyConvertForm)
    );
  }

  /**
   * Bind data from Login form and call to authenticate user.
   * Create session if authentication successful.
   */
  public Result authenticate() {
    Form<User> loginForm = Form.form(User.class).bindFromRequest();
    if (loginForm.hasErrors()) {
      logger.error("Login form has global errors, \n {}", loginForm.errorsAsJson());
      return badRequest(index.render(loginForm));
    }
    if (userService.authenticate(loginForm.get()) == null) {
      logger.error("User not authenticated. Invalid username/password.");
      loginForm.reject("Invalid username/password. Please try again!");
      return badRequest(
          index.render(loginForm)
      );
    }

    session().clear();
    session("username", loginForm.get().getUserName());
    logger.info("New session created for {}", loginForm.get().getUserName());
    return redirect(
        routes.Application.convertCurr()
    );
  }

  /**
   * Bind data from Register form and call to register user in DB.
   */
  public Result registerUser() {
    Form<User> registerForm = Form.form(User.class).bindFromRequest();
    if (registerForm.hasErrors()) {
      logger.error("Register form had errors, \n {}", registerForm.errorsAsJson());
      return badRequest(register.render(registerForm));
    }
    // Check if userName exists in DB.
    if (userService.getUserForName(registerForm.get().getUserName()).size() != 0) {
      logger.error("User: {} already exists in DB, redirected to register again.",
          registerForm.get().getUserName());
      registerForm.reject("userName", "Username already exists in DB. Please pick a new username.");
      return badRequest(register.render(registerForm));
    }

    userService.registerNewUser(registerForm.get());
    logger.info("USER :{} registered in DB.", registerForm.get().getUserName());
    return redirect(
        routes.Application.index()
    );
  }

  /**
   * Logout user session and redirect to login page.
   */
  public Result logout() {
    session().remove("username");
    return redirect(
        routes.Application.index()
    );
  }

  /**
   * Convert Currency
   * @return
   */
  public Result exchangeCurrency() {
    Form<CurrencyConvert> convertCurrencyForm = Form.form(CurrencyConvert.class).bindFromRequest();
    // Check existing session. If session ont found, redirect to convert currency page.
    if(session().get("username") != null) {
      logger.info("Session exists. Redirected to convert currency page.");
      convertCurr();
    }
    // Check form for errors.
    if(convertCurrencyForm.hasErrors()) {
      logger.error("Currency convert form had errors, \n {}", convertCurrencyForm.errorsAsJson());
      return badRequest(convertCurr.render(convertCurrencyForm));
    }
    CurrencyConvert formValue = convertCurrencyForm.get();
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(formValue.getFromValue(), formValue.getFromCurrency(), formValue.getToCurrency());
    Form<CurrencyConvert> resultForm = Form.form(CurrencyConvert.class).fill(result);
    logger.info("Successfully converted. Rendering result now.");
    return ok(
        convertCurr.apply(resultForm)
    );
  }

}

package controllers;

import assets.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.*;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.UserService;
import views.html.*;

import static play.data.Form.form;

/**
 * Created by Subin Sapkota on 2/7/17.
 */
public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    // TODO: Use DI to autowire userService.
    private static final UserService userService = new UserService();

    /**
     * Render Index page
     *
     * @return
     */
    public static Result index() {
        Form<User> loginForm = form(User.class);
        logger.info("Rendered Login page");
        return ok(
                index.render(loginForm)
        );
    }

    /**
     * Render home page
     *
     * @return
     */
    public static Result home(){
        logger.info("Rendered Home page");
        return ok(
                home.render()
        );
    }

    /**
     * Render user sign up page.
     *
     * @return
     */
    public static Result register(){
        Form<User> registrationForm = form(User.class);
        logger.info("Rendered Registration page");
        return ok(
                register.render(registrationForm)
        );
    }

    /**
     * Bind data from Login form and call to authenticate user.
     * Create session if authentication successful.
     *
     * @return
     */
    @Transactional
    public static Result authenticate(){
        Form<User> loginForm = Form.form(User.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            logger.info("Login form has global errors.");
            return badRequest(index.render(loginForm));
        } else {
            // Authenticate user. Create session if successful.
            if(userService.authenticate(loginForm.get()) != null){
                session().clear();
                session("username", loginForm.get().getUserName());
                logger.info("New session created for {}", loginForm.get().getUserName());
                return redirect(
                        routes.Application.home()
                );
            } else{
                logger.info("User not authenticated. Invalid username/password.");
                return redirect(
                        routes.Application.index()
                );
            }
        }
    }

    /**
     * Bind data from Register form and call to register user in DB.
     *
     * @return
     */
    @Transactional
    public static Result registerUser(){
        Form<User> registerForm = Form.form(User.class).bindFromRequest();

        if(registerForm.hasErrors()){
            logger.error("Register form had errors, \n {}", registerForm.errorsAsJson());
            return badRequest(register.render(registerForm));
        } else {
            // Get user from form and persist to database.
            userService.registerUser(registerForm.get());
            return redirect(
                    routes.Application.index()
            );
        }
    }
}

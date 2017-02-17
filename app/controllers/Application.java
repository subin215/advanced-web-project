package controllers;

import assets.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.*;
import play.mvc.*;

import play.mvc.Controller;
import services.spi.IUserService;
import views.html.*;


import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import static play.data.Form.form;

/**
 * Created by Subin Sapkota on 2/7/17.
 */
@Named
public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    private IUserService userService;

    /**
     * Render Index page
     *
     * @return
     */
    public Result index() {
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
    public Result home(){
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
    public Result register(){
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
    public Result authenticate(){
        Form<User> loginForm = Form.form(User.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            logger.info("Login form has global errors, \n {}", loginForm.errorsAsJson());
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
                loginForm.reject("Invalid username/password. Please try again!");
                return badRequest(
                        index.render(loginForm)
                );
            }
        }
    }

    /**
     * Bind data from Register form and call to register user in DB.
     *
     * @return
     */
    public Result registerUser(){
        Form<User> registerForm = Form.form(User.class).bindFromRequest();

        if(registerForm.hasErrors()){
            logger.error("Register form had errors, \n {}", registerForm.errorsAsJson());
            return badRequest(register.render(registerForm));
        } else {
            // Check if userName exists in DB.
            try {
                userService.getUserForName(registerForm.get().getUserName());
            }  catch(NoResultException e){
                // If username doesn't exist in database.
                userService.registerNewUser(registerForm.get());
                logger.info("USER :{} registered in DB.", registerForm.get().getUserName());
                return redirect(
                        routes.Application.index()
                );
            }

            // If userName exists in database
            logger.error("User: {} already exists in DB, redirected to register again.", registerForm.get().getUserName());
            registerForm.reject("userName", "Username already exists in DB. Please pick a new username.");
            return badRequest(register.render(registerForm));

        }
    }

    /**
     * Logout user session and redirect to login page.
     *
     * @return
     */
    public Result logout(){
        session().clear();

        //Redirect to login page.
        Form<User> loginForm = form(User.class);
        return ok(index.render(
                loginForm
        ));
    }

}

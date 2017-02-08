package controllers;

import assets.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.*;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static Result index() {
        logger.info("Rendered Login page");
        return ok(
                index.render(form(Login.class))
        );
    }

    public static Result home(){
        logger.info("Rendered Home page");
        return ok(
                home.render()
        );
    }

    public static Result authenticate(){
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            logger.info("Login form has global errors.");
            return badRequest(index.render(loginForm));
        } else {
            session().clear();
            logger.info("New session created for {}", loginForm.get().username);
            session("username", loginForm.get().username);
            return redirect(
                    routes.Application.home()
            );
        }
    }


    /**
     * Class to be utilized for Login in application
     */
    public static class Login {

        public String username;
        public String password;

        public String validate(){
            if (User.authenticate(username, password) == null){
                return "Invalid user or password";
            }
            return null;
        }
    }

}

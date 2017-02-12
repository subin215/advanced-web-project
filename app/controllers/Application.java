package controllers;

import assets.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.*;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static Result index() {
        Form<Login> loginForm = form(Login.class);
        logger.info("Rendered Login page");
        return ok(
                index.render(loginForm)
        );
    };

    public static Result home(){
        logger.info("Rendered Home page");
        return ok(
                home.render()
        );
    }

    @Transactional
    public static Result authenticate(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            logger.info("Login form has global errors.");
            return badRequest(index.render(loginForm));
        } else {
            session().clear();
            session("username", loginForm.get().username);
            logger.info("New session created for {}", loginForm.get().username);
            return redirect(
                    routes.Application.home()
            );
        }
    }


    /**
     * Class to be utilized for Login in application
     */
    public static class Login {

        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String validate(){
            if (User.authenticate(username, password) == null){
                return "Invalid user or password";
            }
            return null;
        }
    }

}

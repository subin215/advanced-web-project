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
        Form<User> loginForm = form(User.class);
        logger.info("Rendered Login page");
        return ok(
                index.render(loginForm)
        );
    }

    public static Result home(){
        logger.info("Rendered Home page");
        return ok(
                home.render()
        );
    }

    public static Result register(){
        Form<User> registrationForm = form(User.class);
        logger.info("Rendered Registration page");
        return ok(
                register.render(registrationForm)
        );
    }

    @Transactional
    public static Result authenticate(){
        Form<User> loginForm = Form.form(User.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            logger.info("Login form has global errors.");
            return badRequest(index.render(loginForm));
        } else {
            if(User.authenticate(loginForm.get().getUserName(), loginForm.get().getPassword()) != null){
                session().clear();
                session("username", loginForm.get().getUserName());
                logger.info("New session created for {}", loginForm.get().getUserName());

                return redirect(
                        routes.Application.home()
                );
            } else{
                return redirect(
                        routes.Application.index()
                );
            }
        }
    }

    @Transactional
    public static Result registerUser(){
        Form<User> registerForm = Form.form(User.class).bindFromRequest();
            // Get user from form and persist to database.
        if(registerForm.hasErrors()){
            return badRequest(register.render(registerForm));
        } else {
            User user = registerForm.get();
            User.registerUser(user);
            return redirect(
                    routes.Application.index()
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

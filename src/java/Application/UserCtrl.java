package Application;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "userCtrl")
@ViewScoped

public class UserCtrl implements Serializable {
    
    @EJB
    private UserDAO userDAO;
    private User authUser;
    private String loginMessage;
    
    public UserCtrl() {
        authUser = new User();
        loginMessage = "Test";
    }
    
    public String login() {
        List<User> resultLogin = this.userDAO.getUserByNameAndPassword(authUser.getNameUser(), authUser.getPasswordUser());
        if(resultLogin.isEmpty()) { // If nothing was found in database ...
            loginMessage = "Bad username or password";
            return null;
        } else { // The user is good so we connect it !
            loginMessage = "Connecté";
            return "dashboard";
        }
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getAuthUser() {
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
    
    

}
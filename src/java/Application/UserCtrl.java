package Application;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "userCtrl")
@ViewScoped

public class UserCtrl implements Serializable {
    
    @EJB
    private UserDAO userDAO;
    private User authUser;
    private User loggedUser;
    private String loginMessage;
    
    public UserCtrl() {
        authUser = new User();
        loginMessage = "Test";
    }
    
    public String login() {
        User resultLogin = this.userDAO.getUserByNameAndPassword(authUser.getNameUser(), authUser.getPasswordUser());
        if (resultLogin != null) {
            loggedUser = resultLogin;
            return "dashboard";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
            return "login";
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
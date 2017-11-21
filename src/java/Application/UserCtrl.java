package Application;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "userCtrl")
@SessionScoped

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
            try{ 
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
            } catch (IOException ex) {}
            return "dashboard";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid login", "Please try again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login";
        }
    }
    
    public String disconnect() {
        loggedUser = null;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Disconnected", "You've been disconnected");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "index.xhtml";
    }
    
    public boolean isUserLogged() {
        return (loggedUser!=null);
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
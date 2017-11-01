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
    
    public UserCtrl() {
        authUser = new User();
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

}
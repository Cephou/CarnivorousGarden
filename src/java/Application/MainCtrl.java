/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Laurent
 */
@Named(value = "mainCtrl")
@ViewScoped

public class MainCtrl implements Serializable {

    //@EJB
    //private PlantDAO plantDAO;

    public MainCtrl() {
    }
    
}

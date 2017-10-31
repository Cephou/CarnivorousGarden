/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "mainCtrl")
@ViewScoped

public class MainCtrl implements Serializable {

    @EJB
    private PlantDAO plantDAO;
    @EJB
    private UserDAO userDAO;
    
    private Plant newPlant;

    public MainCtrl() {
        newPlant = new Plant();
    }
    
    public List<User> getUsers() {
        return userDAO.allUsers();
    }
        
    public List<Plant> getPlants() {
        return plantDAO.allPlants();
    }
    
    public void addPlant() {
        plantDAO.add(this.newPlant);
        this.newPlant = new Plant();
    }
    
    public PlantDAO getPlantDAO() {
        return plantDAO;
    }

    public void setPlantDAO(PlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    public Plant getNewPlant() {
        return newPlant;
    }

    public void setNewPlant(Plant newPlant) {
        this.newPlant = newPlant;
    }
    
}

package Application;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named(value = "plantCtrl")
@ViewScoped

public class PlantCtrl implements Serializable {

    @EJB
    private PlantDAO plantDAO;
    private Plant newPlant;

     
    public PlantCtrl() {
        newPlant = new Plant();
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

package Application;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named(value = "plantCtrl")
@ViewScoped

public class PlantCtrl implements Serializable {

    @EJB
    private PlantDAO plantDAO;
    private Plant newPlant;
    private Plant selectedPlant;
    private List<Plant> displayPlants;
    private String selectedGenus;
     
    public PlantCtrl() {
        newPlant = new Plant();
    }
    
    @PostConstruct
    public void init(){
        displayPlants = this.getPlants();
        selectedGenus = "";
    }
   
    public List<Plant> getPlants() {
        return plantDAO.allPlants();
    }
    
    public List<Plant> getGenuses() {
        return plantDAO.plantGenuses();
    }
    
    public void changePlantList(){  
        if(!this.selectedGenus.isEmpty()) {
            displayPlants = plantDAO.getPlantsByGenus(selectedGenus);
        } else {
            displayPlants = this.getPlants();
        }
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

    public Plant getSelectedPlant() {
        return selectedPlant;
    }

    public void setSelectedPlant(Plant selectedPlant) {
        this.selectedPlant = selectedPlant;
    }

    public List<Plant> getDisplayPlants() {
        return displayPlants;
    }

    public void setDisplayPlants(List<Plant> displayPlants) {
        this.displayPlants = displayPlants;
    }

    public String getSelectedGenus() {
        return selectedGenus;
    }

    public void setSelectedGenus(String selectedGenus) {
        this.selectedGenus = selectedGenus;
    }
    
}

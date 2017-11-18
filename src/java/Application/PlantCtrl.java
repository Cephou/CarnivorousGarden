package Application;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


@Named(value = "plantCtrl")
@ViewScoped

public class PlantCtrl implements Serializable {

    @EJB
    private PlantDAO plantDAO;
    private Plant newPlant;
    private Plant selectedPlant;
    private List<Plant> displayPlants;
    private String selectedGenus;
    private List<Plant> plants;
     
    public PlantCtrl() {
    }
    
    @PostConstruct
    public void init(){
        plants = plantDAO.allPlants();
        newPlant = new Plant();
        displayPlants = this.getPlants();
        selectedGenus = "";
    }
   
    public List<Plant> getPlants() {
        return plants;
    }
    
    public List<Plant> getGenuses() {
        return plantDAO.plantGenuses();
    }
    
    public Plant getPlantById(int id) {
        return plantDAO.getPlantById(id);
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
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Plant Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            plantDAO.edit((Plant)newValue);
        }
    }
}

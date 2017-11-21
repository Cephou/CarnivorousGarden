package Application;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


@Named(value = "plantCtrl")
@SessionScoped

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
        selectedGenus = "all";
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
        if(!"all".equals(this.selectedGenus)) {
            displayPlants = plantDAO.getPlantsByGenus(selectedGenus);
        } else {
            displayPlants = plantDAO.allPlants();
        }
    }
    
    public boolean isSoldOut(Plant p) {
        return (p.getNumStock() == 0);
    }
    
    public String getStockStatus(Plant p) {
        if(this.isSoldOut(p)) {
            return "Sold out";
        } else {
            return "In stock";
        }
    }
    
    public void removePlant(Plant p) {
        plantDAO.remove(p);
        displayPlants = plantDAO.allPlants();
        plants = plantDAO.allPlants();
    }
    
    public void addPlant() {
        plantDAO.add(this.newPlant);
        File file = new File("L:\\Laurent\\Documents\\NetBeansProjects\\CarnivorousGarden\\web\\img\\plants\\" + newPlant.getIdPlant() + "");
        file.mkdirs();
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
            DataTable s = (DataTable) event.getSource();
            Plant p = (Plant) s.getRowData();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            plantDAO.edit(p);
        }
    }
}

package Application;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "cartCtrl")
@SessionScoped

public class CartCtrl implements Serializable {
    
    @EJB
    private CartDAO cartDAO;
    @EJB
    private PlantDAO plantDAO;
    private Cart currentCart;
    private List<Cart> carts;
    FacesMessage message;

    /**
     * Creates a new instance of CartCtrl
     */
    public CartCtrl() {
    }
    
    @PostConstruct
    public void init() {
        carts = cartDAO.allCarts();
        currentCart = new Cart();
        currentCart.setPlantCollection(new ArrayList<Plant>());
    }
    
    public void addItemToCart(Plant p) {
        currentCart.getPlantCollection().add(p);
    }
    
    public void removeItemFromCart(Plant p) {
        currentCart.getPlantCollection().remove(p);
    }
        
    public int getNumberOfItems() {
        return currentCart.getPlantCollection().size();
    }
    
    public float getTotalValue(Cart c) {
        float total = 0;
        for(Plant plant : c.getPlantCollection()) {
            total += plant.getPricePlant();
	}
        return total;
    }
    
    public void validateCart() {
        cartDAO.add(currentCart);
        int stock;
        for(Plant plant : currentCart.getPlantCollection()) {
            stock = plant.getNumStock();
            plant.setNumStock(stock - 1);
            plantDAO.edit(plant);
        }
        currentCart = new Cart();
        currentCart.setPlantCollection(new ArrayList<Plant>());
        carts = cartDAO.allCarts();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your request has been registered", "Our team will contact you soon.");
        FacesContext.getCurrentInstance().addMessage(null, message);  
        try{ 
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {}
    }

    public CartDAO getCartDAO() {
        return cartDAO;
    }

    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public Cart getCurrentCart() {
        return currentCart;
    }

    public void setCurrentCart(Cart currentCart) {
        this.currentCart = currentCart;
    }

    public PlantDAO getPlantDAO() {
        return plantDAO;
    }

    public void setPlantDAO(PlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
    
    
    
}

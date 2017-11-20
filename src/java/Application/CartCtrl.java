package Application;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "cartCtrl")
@SessionScoped

public class CartCtrl implements Serializable {
    
    @EJB
    private CartDAO cartDAO;
    private PlantDAO plantDAO;
    private Cart currentCart;

    /**
     * Creates a new instance of CartCtrl
     */
    public CartCtrl() {
    }
    
    @PostConstruct
    public void init() {
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
    
    public float getTotalValue() {
        float total = 0;
        for(Plant plant : currentCart.getPlantCollection()) {
            total += plant.getPricePlant();
	}
        return total;
    }
    
    public void validateCart() {
        cartDAO.add(currentCart);
        currentCart = new Cart();
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
    
}

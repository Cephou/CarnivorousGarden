/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Laurent
 */
@Stateless
public class CartDAO {
    
    @PersistenceContext(unitName = "CarnivorousGardenPU")
    private EntityManager em;
    
    public List<Cart> allCarts() {
        Query query = em.createNamedQuery("Cart.findAll");
        return query.getResultList();
    }
    
    public void add(Cart c) {
        em.persist(c);
        em.flush();
    }
    
}

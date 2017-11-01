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

@Stateless
public class UserDAO {
    
    @PersistenceContext(unitName = "CarnivorousGardenPU")
    private EntityManager em;
    
    public List<User> allUsers() {
        Query query = em.createNamedQuery("User.findAll");
        return query.getResultList();
    }
    
    public List<User> getUserByNameAndPassword(String userName, String userPassword) {
        Query query = em.createNamedQuery("User.findByNameAndPassword");
        return query.getResultList();
    }
}


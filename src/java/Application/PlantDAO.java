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
public class PlantDAO {
    
    @PersistenceContext(unitName = "CarnivorousGardenPU")
    private EntityManager em;
    
    public List<Plant> allPlants() {
        Query query = em.createNamedQuery("Plant.findAll");
        return query.getResultList();
    }
    
    public List<Plant> plantGenuses() {
        Query query = em.createNamedQuery("Plant.getAllGenuses");
        return query.getResultList();
    }
    
    public List<Plant> getPlantsByGenus(String pgenus) {
        Query query = em.createNamedQuery("Plant.getPlantsByGenus");
        query.setParameter("genusPlant", pgenus);
        return query.getResultList();
    }
    
    public void add(Plant p) {
        em.persist(p);
        em.flush();
    }
    
    public void edit(Plant p) {
        em.merge(p);
        em.flush();
    }

    public void remove(Plant p) {
        em.remove(em.merge(p));
        em.flush();
    }
}


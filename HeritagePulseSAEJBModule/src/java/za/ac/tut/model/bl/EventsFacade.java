/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.model.entity.Events;

/**
 *
 * @author lucas
 */
@Stateless
public class EventsFacade extends AbstractFacade<Events> implements EventsFacadeLocal {

    @PersistenceContext(unitName = "HeritagePulseSAEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventsFacade() {
        super(Events.class);
    }
    
    public List<Events> findNearbyEvents(double userLat, double userLng, double radiusKm) {
    // Basic logic: Fetch events from DB. 
    return em.createQuery("SELECT e FROM Events e WHERE e.privacyLevel = 'Public'", Events.class).getResultList();
}
    
}

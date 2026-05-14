/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.model.entity.Events;

/**
 *
 * @author lucas
 */
@Local
public interface EventsFacadeLocal {

    void create(Events events);

    void edit(Events events);

    void remove(Events events);

    Events find(Object id);

    List<Events> findAll();

    List<Events> findRange(int[] range);

    int count();
    
    //additional methods
    public List<Events> findNearbyEvents(double userLat, double userLng, double radiusKm);
    
}

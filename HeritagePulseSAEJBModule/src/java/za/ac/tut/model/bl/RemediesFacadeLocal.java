/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.model.entity.Remedies;


@Local
public interface RemediesFacadeLocal {

    void create(Remedies remedies);

    void edit(Remedies remedies);

    void remove(Remedies remedies);

    Remedies find(Object id);

    List<Remedies> findAll();

    List<Remedies> findRange(int[] range);

    int count();
    
    //additional methods
    public List<Remedies> searchByIndigenousName(String name);
    
}

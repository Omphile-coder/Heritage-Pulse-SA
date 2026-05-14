/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.model.entity.Remedies;

/**
 *
 * @author lucas
 */
@Stateless
public class RemediesFacade extends AbstractFacade<Remedies> implements RemediesFacadeLocal {

    @PersistenceContext(unitName = "HeritagePulseSAEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RemediesFacade() {
        super(Remedies.class);
    }
    
    
    @Override
    public List<Remedies> searchByIndigenousName(String name) {
    List<Remedies> list = em.createQuery("SELECT r FROM Remedies r WHERE LOWER(r.indigenousName) LIKE :name", Remedies.class)
             .setParameter("name", "%" + name.toLowerCase() + "%")
             .getResultList();
             
   
        for(Remedies r : list) {
        if(r.getSafetyDisclaimer() == null) {
            r.setSafetyDisclaimer("Consult a professional before use.");
        }
    }
    return list;
}
    
}

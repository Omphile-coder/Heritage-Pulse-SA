/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.model.entity.Users;
import za.ac.tut.model.bl.AbstractFacade;
import za.ac.tut.model.entity.ArchiveItems;

/**
 *
 * @author lucas
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "HeritagePulseSAEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public List<ArchiveItems> getPulseRecommendations(Integer userId) {
       Users user = em.find(Users.class, userId);
    
    // 2. Ensure the user exists before querying
    if (user == null || user.getFollowedTribeId() == null) {
        return new java.util.ArrayList<>();
    }

    // 3. Query the archive using the correct singular Entity name
    return em.createQuery("SELECT a FROM ArchiveItems a WHERE a.tribeId = :t").setParameter("t", user.getFollowedTribeId()).getResultList();
    }
   @Override
    public Users login(String email, String password) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.email = :e AND u.password = :p", Users.class)
                     .setParameter("e", email)
                     .setParameter("p", password)
                     .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(); // This will print the actual error in your NetBeans console!
            return null; 
        }
    }
}

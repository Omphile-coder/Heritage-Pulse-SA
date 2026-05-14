/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.bl;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.model.entity.ArchiveItems;

/**
 *
 * @author lucas
 */
@Stateless
public class ArchiveItemsFacade extends AbstractFacade<ArchiveItems> implements ArchiveItemsFacadeLocal {

    @PersistenceContext(unitName = "HeritagePulseSAEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArchiveItemsFacade() {
        super(ArchiveItems.class);
    }
    
    @Override
    public List<ArchiveItems> filterByTribe(Integer tribeId) {
    
    return em.createQuery("SELECT a FROM ArchiveItems a WHERE a.tribeId.tribeId = :tId")
             .setParameter("tId", tribeId)
             .getResultList();
}

    @Override
    public List<ArchiveItems> filterByCategory(String category) {
    
    return em.createQuery("SELECT a FROM ArchiveItems a WHERE a.category = :cat")
             .setParameter("cat", category)
             .getResultList();
}
    
    @Override
    public List<ArchiveItems> findPendingSubmissions() {
    return em.createQuery("SELECT a FROM ArchiveItems a WHERE a.status = 'Pending'", ArchiveItems.class)
             .getResultList();

    
    }

    @Override
    public List<ArchiveItems> findApprovedStories() {
    
    return em.createQuery("SELECT a FROM ArchiveItems a WHERE a.status = 'Approved' AND a.description IS NOT NULL ORDER BY a.itemId DESC", ArchiveItems.class)
             .getResultList();
}
    
    
    // Inside ArchiveItemsFacade.java
    @Override
    public List<ArchiveItems> findMediaRichContent() {
    // Fetches approved items that have an associated media file
    return em.createQuery("SELECT a FROM ArchiveItems a WHERE a.status = 'Approved' AND a.filePath IS NOT NULL", ArchiveItems.class)
             .getResultList();
}
    
    // Inside ArchiveItemsFacade.java
    @Override
    public List<ArchiveItems> searchChronicles(String keyword, Integer tribeId) {
    StringBuilder queryStr = new StringBuilder("SELECT a FROM ArchiveItems a WHERE a.status = 'Approved' AND a.description IS NOT NULL");
    
    // Dynamically append conditions
    if (keyword != null && !keyword.trim().isEmpty()) {
        queryStr.append(" AND (LOWER(a.title) LIKE :kw OR LOWER(a.description) LIKE :kw)");
    }
    if (tribeId != null) {
        queryStr.append(" AND a.tribeId.tribeId = :tId");
    }
    
    queryStr.append(" ORDER BY a.itemId DESC");

    
    TypedQuery<ArchiveItems> query = em.createQuery(queryStr.toString(), ArchiveItems.class);
    
    // Set the parameters if they were added to the string
    if (keyword != null && !keyword.trim().isEmpty()) {
        query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
    }
    if (tribeId != null) {
        query.setParameter("tId", tribeId);
    }
    
    return query.getResultList();
}
    
}

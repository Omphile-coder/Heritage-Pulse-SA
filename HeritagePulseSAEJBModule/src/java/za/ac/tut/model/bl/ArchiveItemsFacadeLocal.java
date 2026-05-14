/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.model.entity.ArchiveItems;

/**
 *
 * @author lucas
 */
@Local
public interface ArchiveItemsFacadeLocal {

    void create(ArchiveItems archiveItems);

    void edit(ArchiveItems archiveItems);

    void remove(ArchiveItems archiveItems);

    ArchiveItems find(Object id);

    List<ArchiveItems> findAll();

    List<ArchiveItems> findRange(int[] range);

    int count();
    
    //additional methods
    public List<ArchiveItems> filterByTribe(Integer tribeId);
    public List<ArchiveItems> filterByCategory(String category);
    public List<ArchiveItems> findPendingSubmissions();

    public List<ArchiveItems> findApprovedStories();
    public List<ArchiveItems> findMediaRichContent();
    public List<ArchiveItems> searchChronicles(String keyword, Integer tribeId);
    
}

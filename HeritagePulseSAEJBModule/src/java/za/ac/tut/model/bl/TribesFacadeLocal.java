package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.model.entity.Tribes;

/**
 *
 * @author lucas
 */
@Local
public interface TribesFacadeLocal {

    void create(Tribes tribes);

    void edit(Tribes tribes);

    void remove(Tribes tribes);

    Tribes find(Object id);

    List<Tribes> findAll();

    List<Tribes> findRange(int[] range);

    int count();
    
}

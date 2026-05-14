package za.ac.tut.model.bl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.model.entity.Tribes;


@Stateless
public class TribesFacade extends AbstractFacade<Tribes> implements TribesFacadeLocal {

    @PersistenceContext(unitName = "HeritagePulseSAEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TribesFacade() {
        super(Tribes.class);
    }
    
}

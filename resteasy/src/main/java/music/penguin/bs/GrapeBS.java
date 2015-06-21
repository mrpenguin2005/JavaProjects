package music.penguin.bs;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import music.penguin.domain.Grape;
import music.penguin.domain.Wine;

public class GrapeBS implements Serializable {

	private static final long serialVersionUID = 3203700955812348506L;
	
	@PersistenceContext EntityManager em;
	
	@Inject UserTransaction utx;
	@Inject ORMUtils ormUtils;

//	@Transactional(propagation=Propagation.NOT_SUPPORTED)
//	public List<Grape> retrieveAllGrapes() {
//		return grapeDAO.retrieveGrapeList();
//	}

	public Grape retrieveGrapes() {
		Grape grape = null;
		try {
			utx.begin();
			grape = em.find(Grape.class,2L);
			for (Wine w : grape.getWines()) {
				w.getSynonyms().size();
				w.getGrapes().size();
			}
			utx.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grape;
	}
}

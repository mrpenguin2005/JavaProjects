package music.penguin.bs;

import java.io.Serializable;

import javax.ejb.Stateful;
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

@Stateful
public class GrapeBS implements Serializable {

	private static final long serialVersionUID = 3203700955812348506L;
	
	@PersistenceContext EntityManager em;
	
	@Inject UserTransaction utx;
	@Inject ORMUtils ormUtils;

//	@Transactional(propagation=Propagation.NOT_SUPPORTED)
//	public List<Grape> retrieveAllGrapes() {
//		return grapeDAO.retrieveGrapeList();
//	}

	public Grape retrieveGrape() {
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
	
	//@TransactionAttribute(TransactionAttributeType.NEVER)
	public Grape retrieveGrape1() {
		Grape grape = null;
		try {
			System.err.println( "Transaction Active : " + utx.getStatus());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.err.println("Transaction active? {}" + (tsr.getTransactionStatus() == Status.STATUS_ACTIVE));
		grape = em.find(Grape.class,2L);
		for (Wine w : grape.getWines()) {
			//w.getSynonyms().size();
			//w.getGrapes().size();
			ormUtils.initializeAndUnproxy(w.getSynonyms());
			ormUtils.initializeAndUnproxy(w.getGrapes());
		}
		return grape;
	}
}

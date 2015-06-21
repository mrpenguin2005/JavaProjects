package music.penguin.bs;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import music.penguin.domain.Grape;

public class GrapeBS implements Serializable {

	@PersistenceContext EntityManager em;
	private static final long serialVersionUID = 3203700955812348506L;
	
	
	@Inject ORMUtils ormUtils;

//	@Transactional(propagation=Propagation.NOT_SUPPORTED)
//	public List<Grape> retrieveAllGrapes() {
//		return grapeDAO.retrieveGrapeList();
//	}

	public Grape retrieveAllGrapes() {
		Grape grape = em.find(Grape.class,2L);
		ormUtils.initializeAndUnproxy(grape.getWines());
		
		return grape;
	}
}

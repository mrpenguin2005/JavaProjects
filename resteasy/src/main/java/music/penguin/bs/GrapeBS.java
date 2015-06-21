package music.penguin.bs;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import music.penguin.domain.Grape;

public class GrapeBS implements Serializable {

	@PersistenceContext EntityManager em;
	private static final long serialVersionUID = 3203700955812348506L;
	
	//@Inject ORMUtils ormUtils;

//	@Transactional(propagation=Propagation.NOT_SUPPORTED)
//	public List<Grape> retrieveAllGrapes() {
//		return grapeDAO.retrieveGrapeList();
//	}

	public Grape retrieveGrapes() {
		Grape grape = em.find(Grape.class,2L);
		grape.getWines().size();
		//ormUtils.initializeAndUnproxy(grape.getWines());
		return grape;
	}
}

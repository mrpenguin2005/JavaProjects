package music.penguin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import music.penguin.domain.Grape;

public class GrapeDAO implements Serializable {
	
	private static final long serialVersionUID = -3648735723580403769L;
	
	@PersistenceContext EntityManager em;

	public GrapeDAO() {
	}
	
	@SuppressWarnings("unchecked")
	public List<Grape> retrieveGrapeList() {
		Query query = em.createNamedQuery("GrapeBS.retrieveGrapeList");
		return query.getResultList();
	}

}

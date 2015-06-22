package music.penguin.bs;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import music.penguin.dao.GrapeDAO;
import music.penguin.domain.Grape;
import music.penguin.domain.Profile;
import music.penguin.domain.User;
import music.penguin.domain.Wine;

@Stateful
public class GrapeBS implements Serializable {

	private static final long serialVersionUID = 3203700955812348506L;
	
	@PersistenceContext EntityManager em;
	
	@Inject ORMUtils ormUtils;
	@Inject GrapeDAO grapeDAO;

	public List<Grape> retrieveAllGrapes() {
		return grapeDAO.retrieveGrapeList();
	}

	public Grape retrieveGrape() {
		Grape grape = null;
		grape = em.find(Grape.class,2L);

		for (Wine w : grape.getWines()) {
			//w.getSynonyms().size();
			//w.getGrapes().size();
			ormUtils.initializeAndUnproxy(w.getSynonyms());
			ormUtils.initializeAndUnproxy(w.getGrapes());
			em.find(User.class,w.getUser().getId());
			em.find(Profile.class,w.getUser().getProfile().getId());
		}
		return grape;
	}
}

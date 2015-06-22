package music.penguin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import music.penguin.domain.Wine;
import music.penguin.dto.WineDTO;


public class WineDAO {
	
	@PersistenceContext EntityManager em;
	
	public WineDAO() {} 
	
	public void addWine(Wine wine) {
		em.persist(wine);
	}
	
	public void updateWine(Wine wine) {
		em.merge(wine);
	}
	
	public Wine getWineById(long id) {
		return (Wine) em.find(Wine.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Wine> retrieveWineList() {
		Query query = em.createNamedQuery("WineBS.retrieveWineList");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked") 
	public List<WineDTO> retrieveWineDTOList(Long userId) {
		Query query = em.createNamedQuery("WineBS.retrieveWineDTOList");
		query.setParameter("userId", userId);
		return query.getResultList();
	}


}

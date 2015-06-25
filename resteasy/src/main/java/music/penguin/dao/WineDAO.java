package music.penguin.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import music.penguin.domain.Wine;

@Stateless
public class WineDAO implements Serializable {
	
	private static final long serialVersionUID = -3248899803029946147L;
	
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
	public List<Wine> retrieveWineList(Long userId) {
		Query query = em.createNamedQuery("WineBS.retrieveWineList");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}

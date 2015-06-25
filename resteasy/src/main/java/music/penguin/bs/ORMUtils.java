package music.penguin.bs;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;

@Stateless
public class ORMUtils implements Serializable {

	private static final long serialVersionUID = -3414490048495414958L;
	
	@PersistenceContext EntityManager em;

	@SuppressWarnings("unchecked")
	public <T> T initializeAndUnproxy(T entity) {
		
		if (entity == null) {
			throw new NullPointerException("Entity passed for initialization is null");
		}

		PersistenceUnitUtil unitUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();

		if (!unitUtil.isLoaded(entity)) {
			((Collection<T>)entity).iterator().hasNext();
			
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T initializeAndUnproxy(T entity, EntityManager emx) {
		
		if (entity == null) {
			throw new NullPointerException("Entity passed for initialization is null");
		}

		PersistenceUnitUtil unitUtil = emx.getEntityManagerFactory().getPersistenceUnitUtil();

		if (!unitUtil.isLoaded(entity)) {
			//((Collection<T>)entity).iterator().hasNext();
			((Collection<T>)entity).size();
		}
		return entity;
	}

}

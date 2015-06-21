package music.penguin.bs;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;

public class ORMUtils {
	@PersistenceContext static EntityManager em;

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

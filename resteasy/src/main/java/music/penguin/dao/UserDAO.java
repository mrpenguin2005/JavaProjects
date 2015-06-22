package music.penguin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import music.penguin.domain.User;


public class UserDAO {
	
	@PersistenceContext EntityManager em;

	public UserDAO() {
	}

	@SuppressWarnings("unchecked")
	public User retrieveUserById(Long userId) {
		Query query = em.createNamedQuery("UserBS.retrieveUserById");
		query.setParameter("userId", userId);
		List<User> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User retrieveUserByLogin(String userLogin) {
		Query query = em.createNamedQuery("UserBS.retrieveUserByLogin");
		query.setParameter("userLogin", userLogin);
		List<User> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}

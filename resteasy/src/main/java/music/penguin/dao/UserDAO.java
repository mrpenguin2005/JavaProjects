package music.penguin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import music.penguin.domain.Profile;
import music.penguin.domain.User;


public class UserDAO implements Serializable {
	
	private static final long serialVersionUID = -6849110173202041083L;
	
	@PersistenceContext EntityManager em;

	public UserDAO() {
	}

	public User retrieveUserById(Long userId) {
		Query query = em.createNamedQuery("UserBS.retrieveUserById");
		query.setParameter("id", userId);
		return (User)(query.getResultList().get(0));
	}
	
	public Profile retriveProfileById(Long profileId) {
		return em.find(Profile.class, profileId);
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

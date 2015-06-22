package music.penguin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;

import music.penguin.domain.Profile;
import music.penguin.domain.User;


public class UserDAO implements Serializable {
	
	private static final long serialVersionUID = -6849110173202041083L;
	
	@PersistenceContext EntityManager em;

	public UserDAO() {
	}

	public User retrieveUserById(Long userId) {
		PersistenceUnitUtil unitUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
		User user = em.find(User.class,userId); 
		Profile profile = em.find(Profile.class,user.getProfile().getId());
		profile.getName();
		profile.getId();
		user.setProfile(profile);
		System.err.println("--> "+unitUtil.isLoaded(profile));
		return user;
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

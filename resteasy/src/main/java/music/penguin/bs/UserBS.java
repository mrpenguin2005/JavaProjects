package music.penguin.bs;

import javax.ejb.Stateful;
import javax.inject.Inject;

import music.penguin.dao.UserDAO;
import music.penguin.domain.User;
import music.penguin.dto.UserDTO;

@Stateful
public class UserBS {
	@Inject UserDAO userDAO;

	public User retrieveUserById(Long userId) {
		User user = userDAO.retrieveUserById(userId);
		return user;
	}
	
	public User retrieveUserByLogin(String userLogin) {
		return userDAO.retrieveUserByLogin(userLogin);
	}
	
	public UserDTO retrieveUserDTOByLogin(String userLogin) {
		User user = userDAO.retrieveUserByLogin(userLogin);
		if (user != null) {
			return new UserDTO(user);
		}
		return null;
	}
}

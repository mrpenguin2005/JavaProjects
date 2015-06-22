package music.penguin.dto;

import javax.inject.Inject;

import music.penguin.bs.ORMUtils;
import music.penguin.domain.User;

public class UserDTO {
	private Long id;
	private String login;
	private String name;
	private String password;
	private ProfileDTO profile;
	
	@Inject ORMUtils ormUtils;
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.name = user.getName();
		this.password = user.getPassword();
		this.profile = new ProfileDTO(ormUtils.initializeAndUnproxy(user.getProfile()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}
	
}

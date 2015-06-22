package music.penguin.dto;

import music.penguin.domain.Profile;

public class ProfileDTO {
	private Long id;
	private String desc;
	
	public ProfileDTO() {}
	
	public ProfileDTO(Profile profile) {
		this.id = profile.getId();
		this.desc = profile.getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}

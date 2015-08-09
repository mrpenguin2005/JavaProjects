package music.penguin.dto;

import javax.ws.rs.FormParam;

public class WineForm {
	
	@FormParam("id") private Long id;
    @FormParam("name") private String name;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}

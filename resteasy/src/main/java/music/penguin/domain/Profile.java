package music.penguin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_profile")
public class Profile implements Serializable {
	
	private static final long serialVersionUID = -7889127565252398505L;
	
	private Long id;
	private String name;
	
	public Profile() {}
	
	public Profile(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_profile")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

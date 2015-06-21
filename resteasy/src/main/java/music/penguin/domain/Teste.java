package music.penguin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name="tb_teste")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL,
	typing = JsonSerialize.Typing.STATIC)
public class Teste implements Serializable {
	
	private static final long serialVersionUID = -7889127565252398505L;
	
	private Integer id;
	private String name;
	
	public Teste() {}

	@Id
	@Column(name="id_teste")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

package music.penguin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_synonym")
public class Synonym implements Serializable {
	private static final long serialVersionUID = 6855346343753612115L;
	
	private Long id;
	private String synonym;
	private Grape grape;
	
	public Synonym() {}
	
	@Id
	@Column(name="id_synonym")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="synonym")
	public String getSynonym() {
		return synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_grape")
	public Grape getGrape() {
		return grape;
	}

	public void setGrape(Grape grape) {
		this.grape = grape;
	}

	@Override
	public String toString() {
		return "Synonym [id=" + id + ", synonym=" + synonym + "]";
	}
	
}

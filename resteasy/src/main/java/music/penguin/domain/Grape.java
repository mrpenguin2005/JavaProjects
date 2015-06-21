package music.penguin.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_grape")
public class Grape implements Serializable {
	private static final long serialVersionUID = 6855346343753612115L;
	
	private Long id;
	private String name;
	private String color;
	private Collection<Wine> wines;
	
	public Grape() {}
	
	public Grape(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grape")
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
	
	@Column(name="color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy = "grapes",
			targetEntity = Wine.class,
			fetch=FetchType.LAZY
			)
	public Collection<Wine> getWines() {
		return wines;
	}

	public void setWines(Collection<Wine> wines) {
		this.wines = wines;
	}
	
	@Transient
	public void setWine(Wine wine) {
		this.wines = new HashSet<Wine>();
		this.wines.add(wine);
	}

	@Override
	public String toString() {
		return "Grape [id=" + id + ", name=" + name + ", color=" + color + "]";
	}
	
}

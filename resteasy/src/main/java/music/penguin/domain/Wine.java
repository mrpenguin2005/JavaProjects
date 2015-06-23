package music.penguin.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="tb_wine")
public class Wine implements Serializable {
	
	private static final long serialVersionUID = -1368817212346560922L;
	
	private Long id;
	private String name;
	private Integer vintage;
	private String country;
	@JsonBackReference
	private Collection<Grape> grapes;
	private Set<Synonym> synonyms;
	private User user;
	
	public Wine() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_wine")
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

	@Column(name="vintage")
	public Integer getVintage() {
		return vintage;
	}

	public void setVintage(Integer vintage) {
		this.vintage = vintage;
	}

	@Column(name="country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@ManyToMany(targetEntity=Grape.class, cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name="tb_wine_grape",
			joinColumns=@JoinColumn(name="id_wine"),
			inverseJoinColumns=@JoinColumn(name="id_grape")
	)
	public Collection<Grape> getGrapes() {
		return grapes;
	}

	public void setGrapes(Collection<Grape> grapes) {
		this.grapes = grapes;
	}

	@ManyToMany(targetEntity=Synonym.class, cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="tb_wine_synonym",
			joinColumns=@JoinColumn(name="id_wine"),
			inverseJoinColumns=@JoinColumn(name="id_synonym")
	)
	public Set<Synonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<Synonym> synonyms) {
		this.synonyms = synonyms;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

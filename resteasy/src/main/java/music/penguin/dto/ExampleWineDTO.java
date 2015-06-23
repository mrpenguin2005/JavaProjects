package music.penguin.dto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import music.penguin.bs.ORMUtils;
import music.penguin.domain.Grape;
import music.penguin.domain.Synonym;
import music.penguin.domain.Wine;

public class ExampleWineDTO extends GenericDTO<Wine> {
	private Long id;
	private String name;
	private Integer vintage;
	private String country;
	@Ignore
	private List<Grape> grapes;
	@Ignore
	private List<Synonym> synonyms;
	@Ignore
	private UserDTO user;
	
	@Inject ORMUtils ormUtils;
	
	public ExampleWineDTO() {}
	
	public ExampleWineDTO(Wine wine) {
		createDTO(wine);
		
		this.grapes = new ArrayList<Grape>();
		for (Grape grape : wine.getGrapes()) {
			grapes.add(ormUtils.initializeAndUnproxy(grape));
		}
		setGrapes(grapes);
		
		this.synonyms= new ArrayList<Synonym>();
		for (Synonym synonym : wine.getSynonyms()) {
			synonyms.add(ormUtils.initializeAndUnproxy(synonym));
		}
		setSynonyms(synonyms);
		
		this.user = new UserDTO(wine.getUser());
	}
	
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

	public Integer getVintage() {
		return vintage;
	}

	public void setVintage(Integer vintage) {
		this.vintage = vintage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Grape> getGrapes() {
		return grapes;
	}

	public void setGrapes(List<Grape> grapes) {
		this.grapes = grapes;
	}

	public List<Synonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<Synonym> synonyms) {
		this.synonyms = synonyms;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}

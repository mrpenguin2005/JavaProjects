package music.penguin.dto;

import java.util.ArrayList;
import java.util.Collection;

import music.penguin.domain.Grape;
import music.penguin.domain.Wine;

public class GrapeDTO {
	
	private Long id;
	private String name;
	private String color;
	private Collection<WineDTO> wines;
	
	public GrapeDTO() {}
	
	public GrapeDTO(Grape grape) {
		this.id = grape.getId();
		this.name = grape.getName();
		this.color = grape.getColor();
		this.wines = null;
	}
	
	public static Collection<WineDTO> createDTOList(Collection<Wine> wines) {
		Collection<WineDTO> dtoList = new ArrayList<WineDTO>();
		for (Wine wine : wines) {
			WineDTO w = new WineDTO(wine);
			w.getSynonyms().size();
			dtoList.add(w);
		}
		return dtoList;
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
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public Collection<WineDTO> getWines() {
		return wines;
	}
	
	public void setWines(Collection<WineDTO> wines) {
		this.wines = wines;
	}

}

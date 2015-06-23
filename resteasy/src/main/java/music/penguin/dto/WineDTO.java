package music.penguin.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import music.penguin.domain.Grape;
import music.penguin.domain.Synonym;
import music.penguin.domain.Wine;

public class WineDTO {
	private Long id;
	private String name;
	private Integer vintage;
	private String country;
	private Collection<GrapeDTO> grapes;
	private Set<Synonym> synonyms;
	private UserDTO user;
	
	public WineDTO() {}
	
	public WineDTO(Wine wine) {
		this.id = wine.getId();
		this.name = wine.getName();
		this.vintage = wine.getVintage();
		this.country = wine.getCountry();
		this.grapes = null;
		this.synonyms = null;
		this.user = new UserDTO(wine.getUser());
	}
	
	public static Collection<GrapeDTO> createDTOList(Collection<Grape> grapes) {
		Collection<GrapeDTO> dtoList = new ArrayList<GrapeDTO>();
		for (Grape grape : grapes) {
			GrapeDTO g = new GrapeDTO(grape);
			dtoList.add(g);
		}
		return dtoList;
	}
	
	public static<D,T> Collection<D> createDTOList1(Class<D> clazz, Collection<T> attributes) {
		Collection<D> dtoList = new ArrayList<D>();
		for (T attribute : attributes) {
			D g;
			try {
				g = clazz.newInstance();
				Method m = g.getClass().getMethod("setGrapeDTO", attribute.getClass());
				m.invoke(m, attribute);
				dtoList.add(g);
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dtoList;
	}
	
	public static<D,T> Set<D> createDTOSet(Class<D> clazz, Collection<T> attributes) {
		Set<D> dtoList = new HashSet<D>();
		for (T attribute : attributes) {
			D g;
			try {
				g = clazz.newInstance();
				Method m = g.getClass().getMethod("setGrapeDTO", attribute.getClass());
				m.invoke(m, attribute);
				dtoList.add(g);
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public Collection<GrapeDTO> getGrapes() {
		return grapes;
	}
	
	public void setGrapes(Collection<GrapeDTO> grapes) {
		this.grapes = grapes;
	}
	
	public Set<Synonym> getSynonyms() {
		return synonyms;
	}
	
	public void setSynonyms(Set<Synonym> synonyms) {
		this.synonyms = synonyms;
	}
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}

package music.penguin.bs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import music.penguin.dao.GrapeDAO;
import music.penguin.domain.Grape;
import music.penguin.dto.GrapeDTO;

@Stateful
public class GrapeBS implements Serializable {

	private static final long serialVersionUID = 3203700955812348506L;
	
	@PersistenceContext EntityManager em;
	
	@Inject ORMUtils ormUtils;
	@Inject GrapeDAO grapeDAO;
	@Inject UserBS userBS;

	public List<Grape> retrieveAllGrapes() {
		return grapeDAO.retrieveGrapeList();
	}
	
	public List<GrapeDTO> retrieveAllGrapesDTO() {
		List<Grape> grapes = grapeDAO.retrieveGrapeList();
		List<GrapeDTO> grapesDTO = new ArrayList<GrapeDTO>();
		for (Grape grape : grapes) {
			GrapeDTO grapeDTO = new GrapeDTO(grape);
			grapeDTO.setWines(GrapeDTO.createDTOList(grape.getWines()));
			grapesDTO.add(grapeDTO);
		}
		return grapesDTO;
	}

	public GrapeDTO retrieveGrape() {
		Grape grape = null;
		grape = em.find(Grape.class,2L);

		GrapeDTO dto = new GrapeDTO(grape);
		dto.setWines(GrapeDTO.createDTOList(grape.getWines()));
		return dto;
	}
	
}

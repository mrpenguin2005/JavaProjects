package music.penguin.bs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import music.penguin.dao.WineDAO;
import music.penguin.domain.Grape;
import music.penguin.domain.Synonym;
import music.penguin.domain.Wine;
import music.penguin.dto.WineDTO;

@Stateful
public class WineBS {
	@Inject WineDAO wineDao;
	@Inject ORMUtils ormUtils;
	
	public void addWine(Wine wine) {
		wineDao.addWine(wine);
	}
	
	public void updateWine(Wine wine) {
		wineDao.updateWine(wine);
	}

	public WineDTO getWineDTOById(long id) {
		WineDTO wineDTO = new WineDTO();
		
		Wine wine = wineDao.getWineById(id);
		
		List<Grape> grapes = new ArrayList<Grape>();
		for (Grape grape : wine.getGrapes()) {
			grapes.add(ormUtils.initializeAndUnproxy(grape));
		}
		wineDTO.setGrapes(grapes);
		
		List<Synonym> synonyms = new ArrayList<Synonym>();
		for (Synonym synonym : wine.getSynonyms()) {
			synonyms.add(ormUtils.initializeAndUnproxy(synonym));
		}
		wineDTO.setSynonyms(synonyms);
		
		return wineDTO;
	}
	
	public Wine getWineById(long id) {
		return wineDao.getWineById(id);
	}
	
	public List<Wine> retrieveWineList() {
		return wineDao.retrieveWineList();
	}
	
	public List<WineDTO> retrieveWineDTOList(Long userId) {
		return wineDao.retrieveWineDTOList(userId);
	}
}

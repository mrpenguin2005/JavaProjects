package music.penguin.bs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import music.penguin.dao.WineDAO;
import music.penguin.domain.Wine;
import music.penguin.dto.WineDTO;

@Stateful
public class WineBS {
	@Inject WineDAO wineDAO;
	@Inject ORMUtils ormUtils;
	
	public void addWine(Wine wine) {
		wineDAO.addWine(wine);
	}
	
	public void updateWine(Wine wine) {
		wineDAO.updateWine(wine);
	}

	public WineDTO getWineDTOById(long id) {
		WineDTO wineDTO;
		
		Wine wine = wineDAO.getWineById(id);
		wine.getSynonyms().size();
		wineDTO = new WineDTO(wine);
		wineDTO.setGrapes(WineDTO.createDTOList(wine.getGrapes()));
		
		return wineDTO;
	}
	
	public Wine getWineById(long id) {
		return wineDAO.getWineById(id);
	}
	
	public List<Wine> retrieveWineList() {
		return wineDAO.retrieveWineList(null);
	}
	
	public List<WineDTO> retrieveWineDTOList(Long userId) {
		List<WineDTO> winesDTO = new ArrayList<WineDTO>();		
		List<Wine> wines = wineDAO.retrieveWineList(userId);
		for (Wine wine : wines) {
			winesDTO.add(new WineDTO(wine));
		}
		
		return winesDTO;
	}
	
//	List fruitList = s.createQuery(
//			  "select f.shape as shape, f.name as name from Fruit f where f.shape = :shape;")
//			  .setParameter("shape", paramShape)
//			  .setResultTransformer( Transformers.aliasToBean(FruitSearchTO.class))
//			  .list();
//			FruitSearchTOdto = (FruitSearchTO) fruitList .get(0);
}

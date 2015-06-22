package music.penguin.dto;

import music.penguin.domain.Wine;

public class TestDTO {

	public static void main(String[] args) {
		WineDTO wineDTO;

		Wine wine = new Wine();
		wine.setCountry("country");
		wine.setId(new Long(111));
		wine.setName("teste");
		wine.setVintage(2222);

		wineDTO = new WineDTO();
		wineDTO.createDTO(wine);

		System.out.println("--------------------");
		System.out.println("setName : " + wineDTO.getName());
		System.out.println("setCountry : " + wineDTO.getCountry());
		System.out.println("setId : " + wineDTO.getId());
		System.out.println("setVintage : " + wineDTO.getVintage());
		System.out.println("--------------------");

		Wine newWine = new Wine();
		wineDTO.createEntity(newWine);

		System.out.println("setName : " + newWine.getName());
		System.out.println("setCountry : " + newWine.getCountry());
		System.out.println("setId : " + newWine.getId());
		System.out.println("setVintage : " + newWine.getVintage());
	}

}

package music.penguin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import music.penguin.bs.GrapeBS;
import music.penguin.bs.ORMUtils;
import music.penguin.bs.UserBS;
import music.penguin.bs.WineBS;
import music.penguin.domain.User;
import music.penguin.dto.GrapeDTO;
import music.penguin.dto.WineDTO;

@Path("/data")
public class Data {
	@Inject ORMUtils ormUtils; 
	@Inject GrapeBS grapeBS;
	@Inject WineBS wineBS;
	@Inject UserBS userBS;

	public Data() {}
	
	@GET
	@Path("/grape")
	@Produces("application/json;charset=utf-8")
	public GrapeDTO getGrape() {
		GrapeDTO grape = grapeBS.retrieveGrape();
		return grape;
	}
	
	@GET
	@Path("/grapes")
	@Produces("application/json;charset=utf-8")
	public List<GrapeDTO> getGrapes() {
		List<GrapeDTO> grapes = grapeBS.retrieveAllGrapesDTO();
		if (grapes == null) {
			grapes = new ArrayList<GrapeDTO>();
		}
		return grapes;
	}
	
	@GET
	@Path("/wine")
	@Produces("application/json;charset=utf-8")
	public WineDTO getWine() {
		WineDTO wine = wineBS.getWineDTOById(61L);
		return wine;
	}
	
	@GET
	@Path("/wine/user")
	@Produces("application/json;charset=utf-8")
	public List<WineDTO> getWineUser() {
		List<WineDTO> wine = wineBS.retrieveWineDTOList(2L);
		return wine;
	}
	
	@GET
	@Path("/user")
	@Produces("application/json;charset=utf-8")
	public User getUser() {
		User user = userBS.retrieveUserById(2L);
		return user;
	}

}

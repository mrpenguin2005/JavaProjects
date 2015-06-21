package music.penguin;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import music.penguin.bs.GrapeBS;
import music.penguin.bs.ORMUtils;
import music.penguin.domain.Grape;
import music.penguin.domain.Profile;
import music.penguin.domain.Teste;

@Path("/data")
public class Data {
	@PersistenceContext EntityManager em;
	@Inject ORMUtils ormUtils; 
	@Inject GrapeBS grapeBS;

	private Bean info;

	public Data() {
		this.info = new Bean();
		this.info.setName("John");
		this.info.setAge(40);
	}

	@GET
	@Path("/info")
	@Produces("application/json;charset=utf-8")
	public Bean getInfo() {
		em.find(Grape.class, 2L);
		return info;
	}

	public void setInfo(Bean info) {
		this.info = info;
	}
	
	@GET
	@Path("/profile")
	@Produces("application/json;charset=utf-8")
	public Profile getProfile() {
		Teste teste = em.find(Teste.class,1);
		if (teste != null) {
			System.err.println( "Teste.name : "+teste.getName());
		}
		Profile profile = em.find(Profile.class, 1L);
		
		return profile;
	}
	
	@GET
	@Path("/grape")
	@Produces("application/json;charset=utf-8")
	public Grape getGrape() {
		Grape grape = grapeBS.retrieveGrapes();
		return grape;
	}

}

package music.penguin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/data")
public class Data {

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
		return info;
	}

	public void setInfo(Bean info) {
		this.info = info;
	}

}

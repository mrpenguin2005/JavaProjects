package music.penguin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/data")
public class Data {

	private String info;

	public Data() {
		this.info = "text data";
	}

	@GET
	@Path("/info")
	@Produces("application/json;charset=utf-8")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}

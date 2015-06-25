package music.penguin;

import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@Provider
@PreMatching
public class WineCorsFilter extends CorsFilter {

	public WineCorsFilter() {
		super();
		setAllowedHeaders("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		setAllowedMethods("POST, GET, HEAD, PUT, DELETE, OPTIONS");
		//getAllowedOrigins().add("*");
	}

}
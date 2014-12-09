package camel.netty;

public class NettyBean {
	
	private String message;
	
	public NettyBean() {
		this.message = "dummy";
	}

	public void message(String message) {
		System.err.println("Before "+this.message);
		this.message = message;
		System.err.println("After "+this.message);
	}

}

package camel.netty;

public class NettyBean {
	
	private String message;
	
	public NettyBean() {
		this.message = "dummy";
	}

	public void message(String message) {
		System.err.println("Before "+this.message);
		this.message = message;
		//waitSeconds(1);
		System.err.println("After "+this.message);
	}
	
	public static void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			return;
		}
	}

}

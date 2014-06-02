package camelinaction;

public class GenericTestRunnable implements Runnable {
	private String msg;
	private Integer seconds;

	public GenericTestRunnable(String msg, Integer seconds) {
		this.msg = msg;
		this.seconds = seconds;
	}
	
	public void run() {
		System.err.println("Start - Generic Do Nothing Thread. Sleep for "+seconds+"s. "+msg);
		try {
			Thread.sleep(seconds*1000);
		} catch(InterruptedException e) {
			System.err.println("Task Exception "+e.getMessage());
		}
		System.err.println("End   - Generic Do Nothing Thread. Sleep for "+seconds+"s. "+msg);
	}

}

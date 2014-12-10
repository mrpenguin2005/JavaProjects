package camel.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public class Task implements Runnable {
	Socket socket;
	CamelContext context;
	
	public Task(Socket socket, CamelContext context) {
		this.socket = socket;
		this.context = context;
	}
	
	public void run() {
		ProducerTemplate producer = context.createProducerTemplate();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = in.readLine();
			while(msg != null) {
				producer.sendBody("direct:ooo", msg);
				msg = in.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

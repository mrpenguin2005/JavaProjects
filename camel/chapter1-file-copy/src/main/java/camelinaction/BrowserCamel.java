package camelinaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.BrowsableEndpoint;

public class BrowserCamel {

	public static void main(String args[]) throws Exception {
		// create CamelContext
		final CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");

		//context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addComponent("activemq", ActiveMQComponent.jmsComponent(connectionFactory));

		// start the route and let it do its work
		context.start();
		
		BrowsableEndpoint endpoint = 
				context.getEndpoint("activemq:queue:processoControle-tes",BrowsableEndpoint.class); 
		int messageCount = endpoint.getExchanges().size();
		System.err.println( "Message Count : "+messageCount);
		List<Exchange>  queue = endpoint.getExchanges();
		for (Exchange e : queue) {
			Long timestamp = (Long)(e.getIn().getHeaders().get("JMSTimestamp"));
			showTimeFull(timestamp);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					context.stop();
					waitSeconds(1);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		System.err.println("Started.");
		waitForever();
		// stop the CamelContext
		//context.stop();
	}
	
	public static void showTimeFull(Long timestamp) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestamp);
		System.err.print("TimeStamp : "+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
		System.err.println(":"+c.get(Calendar.SECOND)+"."+c.get(Calendar.MILLISECOND));
	}
	
	public static void waitForever() {
		while (true) {
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public static void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			return;
		}
	}
	
	public static Properties loadConfig(String fileName) {
		Properties prop = new Properties();
		if (fileName == null) {
			fileName = "solicitacoes.alunos.properties";
		}
		try {
			prop.load(new FileInputStream(fileName));
		} catch(FileNotFoundException e) {
			System.err.println("File not found " +e.getMessage());
			System.exit(1);
		} catch(IOException e) {
			System.err.println("IOException "+e.getMessage());
			System.exit(1);
		}
		return prop;
	}

}

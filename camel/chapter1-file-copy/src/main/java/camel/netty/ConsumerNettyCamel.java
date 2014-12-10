package camel.netty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.util.jndi.JndiContext;

public class ConsumerNettyCamel {

	public static void main(String args[]) throws Exception {

		final JndiContext jndiContext = new JndiContext();
		final CamelContext camelContext = new DefaultCamelContext(jndiContext);
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");

		//context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext

		NettyBean nettyBean = new NettyBean();
		jndiContext.bind("nettyBean", nettyBean);
		
		camelContext.addRoutes(new RouteBuilder() {
			public void configure() {
				//RouteDefinition from = from("netty:tcp://localhost:5000?textline=true&sync=false");
				RouteDefinition from = from("direct:ooo");
				
				from.setExchangePattern(ExchangePattern.InOnly);
				from.process(new Processor() {
					
					public void process(Exchange exchange) throws Exception {
						System.err.println("Processor : "+exchange.getIn().getBody());
						waitSeconds(1);
						exchange.getOut().setBody(exchange.getIn().getBody().toString().toUpperCase());
					}
				});
				
				from.beanRef("nettyBean", "message");
			}
		});

		// start the route and let it do its work
		camelContext.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					camelContext.stop();
					waitSeconds(1);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		ServerSocket serverSocket = new ServerSocket(5000);
		Socket clientSocket;
		
		int n = Integer.MAX_VALUE;
		while(n != 0) {
			clientSocket = serverSocket.accept();
			System.err.println("Accepted.");
			new Thread( new Task(clientSocket,camelContext) ).start();
			n--;
		}

		//System.err.println("Started.");
		//waitForever();
		// stop the CamelContext
		//context.stop();
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

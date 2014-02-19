package camelinaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ConsumerCamel {

	public static void main(String args[]) throws Exception {
		// Read Configuration
		//Properties prop = loadConfig("c:\\tmp\\Imagens\\solicitacoes.alunos.properties");
		String propFile = null;
		if (args.length == 1) {
			propFile = args[0];
		}
		Properties prop = loadConfig(propFile);
		final String brokerUrl = prop.getProperty("broker.url");
		final String brokerJmsQueue = prop.getProperty("broker.from.jms");
		final String brokerFileQueue = prop.getProperty("broker.to.path");

		System.out.println("Config File : "+propFile);
		System.out.println("Broker URL  : "+brokerUrl);
		System.out.println("Broker JMS  : "+brokerJmsQueue);
		System.out.println("Broker File : "+brokerFileQueue);
		// create CamelContext
		final CamelContext context = new DefaultCamelContext();
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				//from("jms:queue:solicitacoes").to("file:/var/www/ooo/solicitacoes");
				from(brokerJmsQueue).to(brokerFileQueue);
			}
		});

		// start the route and let it do its work
		context.start();
		
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

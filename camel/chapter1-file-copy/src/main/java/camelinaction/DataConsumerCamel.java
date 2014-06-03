package camelinaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class DataConsumerCamel {
	static ThreadPoolExecutor pool = null;
	
	public static void main(String args[]) throws Exception {
		
		String propFile = null;
		if (args.length == 1) {
			propFile = args[0];
		}
		Properties prop = loadConfig(propFile);
		final String brokerUrl = prop.getProperty("broker.url");
		final String brokerJmsQueue = prop.getProperty("broker.from.jms");

		System.out.println("Config File : "+propFile);
		System.out.println("Broker URL  : "+brokerUrl);
		System.out.println("Broker JMS  : "+brokerJmsQueue);
		// create CamelContext
		final CamelContext context = new DefaultCamelContext();
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		//context.addComponent("jms", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));

		context.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					System.err.println("ShutdownHook invoked. !!!!");
					System.err.println("Thread Pool Active Count : "+pool.getActiveCount());
					System.err.println("Thread Pool Task   Count : "+pool.getTaskCount());
					pool.shutdown();
					pool.awaitTermination(120, TimeUnit.SECONDS);
					context.stop();
					waitSeconds(1);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		ConsumerTemplate template = context.createConsumerTemplate();
		System.err.println("Cache size: "+template.getCurrentCacheSize());
		template.setMaximumCacheSize(1);
		String msg = "";
		Integer delay = 10;
		pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
		System.err.println("Largest Pool Size :" + pool.getLargestPoolSize());
		
//		Exchange e = template.receive(brokerJmsQueue);
//		JmsMessage m = (JmsMessage)e.getIn();
//		System.err.println("----> "+m.getBody());
//		m.getJmsMessage().acknowledge();
		
		do {
			msg = (String)template.receiveBody(brokerJmsQueue);
			executeWhenIdle(pool, new GenericTestRunnable(msg,delay++));
			System.err.println( "Message Received : "+msg);
		} while(!msg.equalsIgnoreCase("fim"));
		
		System.err.println("Started.");
		waitForever();
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
	
	public static void executeWhenIdle(ThreadPoolExecutor p , Runnable task) {
		while (p.getActiveCount() >= 3) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		p.execute(task);
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

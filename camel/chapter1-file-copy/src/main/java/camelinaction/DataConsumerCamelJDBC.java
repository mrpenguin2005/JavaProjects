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

public class DataConsumerCamelJDBC {
	static ThreadPoolExecutor pool = null;
	
	public static void main(String args[]) throws Exception {
		
		String propFile = null;
		if (args.length == 1) {
			propFile = args[0];
		}
		Properties prop = loadConfig(propFile);
		final String brokerUrl = prop.getProperty("broker.url");
		final String brokerJmsQueue = prop.getProperty("broker.from.jms");
		final String jdbcUrl = prop.getProperty("jdbc.url");
		final String jdbcUser = prop.getProperty("jdbc.user");
		final String jdbcPassowrd = prop.getProperty("jdbc.password");

		System.out.println("Config File : "+propFile);
		System.out.println("Broker URL  : "+brokerUrl);
		System.out.println("Broker JMS  : "+brokerJmsQueue);
		
//		JdbcTestRunnable ooo = new JdbcTestRunnable(jdbcUrl,jdbcUser,jdbcPassowrd,255385);
//		ooo.run();
//		System.exit(0);
		
		// create CamelContext
		final CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

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
		Integer codProcesso;
		pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
		System.err.println("Largest Pool Size :" + pool.getLargestPoolSize());
		
		do {
			codProcesso = (Integer)template.receiveBody(brokerJmsQueue);
			executeWhenIdle(pool, new JdbcTestRunnable(jdbcUrl,jdbcUser,jdbcPassowrd,codProcesso));
			System.err.println( "Message Received : "+codProcesso);
		} while(true);
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

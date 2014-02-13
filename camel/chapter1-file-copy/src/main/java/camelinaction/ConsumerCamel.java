package camelinaction;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ConsumerCamel {

	public static void main(String args[]) throws Exception {
		// create CamelContext
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");

		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("jms:queue:solicitacoes").to("file:/var/www/ooo/solicitacoes");
			}
		});

		// start the route and let it do its work
		context.start();
		// Thread.sleep(10000);
		Thread.sleep(3600000);

		// stop the CamelContext
		context.stop();
	}

}

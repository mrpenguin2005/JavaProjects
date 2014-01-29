package activemq.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory;
		Connection connection;
		Session session;

		connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//incomingOrders
		Destination destination = session.createQueue("incomingOrders");
		MessageConsumer messageConsumer = session.createConsumer(destination);
		Message message = messageConsumer.receive();
		
		((ObjectMessage)message).getObject();
		
		connection.stop();
	}

}

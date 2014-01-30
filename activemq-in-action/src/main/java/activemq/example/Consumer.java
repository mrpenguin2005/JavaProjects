package activemq.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQBytesMessage;

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
		ActiveMQBytesMessage byteMessage = (ActiveMQBytesMessage)message;
		int size = byteMessage.getSize();
		byte[] buffer = new byte[size];
		byteMessage.readBytes(buffer,size);
		System.out.println("Size : "+size);
		System.out.println(new String(buffer));
		
		
		connection.stop();
	}

}

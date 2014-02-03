package activemq.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

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

		// Acquire a Connection
		connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");
		connection = connectionFactory.createConnection();
		connection.start();
		// Create Session and set end point
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("incomingOrders");
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		Message message = messageConsumer.receive();
		ActiveMQBytesMessage byteMessage = (ActiveMQBytesMessage)message;
		
		writeMessageToFile(byteMessage);
		
		System.out.println("Close connection");
		messageConsumer.close();
		session.close();
		connection.stop();
		System.out.println("Closed connection");
	}
	
	public static void writeMessageToFile(ActiveMQBytesMessage message) throws Exception {
		int size = message.getSize();
		
		byte[] buffer = new byte[size];
		int br;
		System.out.println("Size : "+size);

		String fileName = "/var/tmp/data/file_";
		//String fileName = "c://tmp/file_";
		
		File file = new File(fileName+new Date().getTime());
		file.createNewFile();
		FileOutputStream fs = new FileOutputStream(file);
		BufferedOutputStream bs = new BufferedOutputStream(fs);
		do {
			br = message.readBytes(buffer,size);
			System.out.println(".. "+br);
			if (br > 0) {
				bs.write(buffer,0,br);
			}
		} while(br > 0);
		bs.close();
		fs.close();
	}

}

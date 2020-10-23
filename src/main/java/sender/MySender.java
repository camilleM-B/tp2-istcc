package sender;

import javax.jms.*;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");

			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			QueueConnection queueConnection = factory.createQueueConnection();

			// Open a session without transaction and acknowledge automatic
			QueueSession queueSession = queueConnection.createQueueSession(true, 1);

			// Start the connection
			queueConnection.start();

			// Create a sender
			QueueSender queueSender = queueSession.createSender(queue);

			// Create a message
			TextMessage textMessage = queueSession.createTextMessage("Hello worlddd");
			System.out.println("SENDER MESSAGE : " + textMessage.toString());

			// Send the message
			queueSender.send(textMessage);

			// Close the session
			queueSender.close();

			// Close the connection
			queueConnection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}

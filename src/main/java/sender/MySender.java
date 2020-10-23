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
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Start the connection
			queueConnection.start();

			// Create a sender
			QueueSender queueSender = queueSession.createSender(queue);
			QueueSender queueSender2 = queueSession.createSender(queue);

			// Create a message
			TextMessage textMessage = queueSession.createTextMessage("Hello worlddd");
			System.out.println("SENDER MESSAGE : " + textMessage.toString());

			// Send the message
			queueSender.send(textMessage);
			queueSender2.send(queueSession.createTextMessage("i'm out world"));

			// Close the session
			queueSender.close();
			queueSender2.close();

			// Close the connection
			queueConnection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}

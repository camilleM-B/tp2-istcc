package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MySenderTopic {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Topic topic = (Topic) applicationContext.getBean("topic");

			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			TopicConnection topicConnection = factory.createTopicConnection();

			// Open a session without transaction and acknowledge automatic
			TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			// Start the connection
			topicConnection.start();

			// Create MessageProducer
			MessageProducer messageProducer = topicSession.createProducer(topic);

			// Create a message
			TextMessage textMessage = topicSession.createTextMessage("Hello worlddd du topiquent");
			System.out.println("SENDER MESSAGE : " + textMessage.toString());

			// Send the message
			messageProducer.send(textMessage);

			// Close the session
			topicSession.close();

			// Close the connection
			topicConnection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}

package receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyReceiverTopic {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Topic topic = (Topic) applicationContext.getBean("topic");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			TopicConnection topicConnection = factory.createTopicConnection();

			// Open a session
			TopicSession topicSession = topicConnection.createTopicSession(true, 1);

			// start the connection
			topicConnection.start();

			// Create a receiver
			TopicReceiver topicReceiver = topicSession.createReceiver(topic);

			// Receive the message
			// Works with two receiving processes
			Message message = topicReceiver.receive();

			System.out.println("\n\n\n\nRECEIVER MESSAGE : " + message.toString() + "\n\n\n");

			topicReceiver.close();
			topicConnection.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

package receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("\n\nMESSAGE RECEIVED : " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
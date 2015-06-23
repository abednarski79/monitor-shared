package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;
import eu.appbucket.monitor.shared.pojo.MessageType;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MessageHandlerImpl implements MessageHandler {

    private static String ACTIVE_MQ_SERVER_URL = "tcp://localhost:61616";
    private static String ACTIVE_MQ_SUBJECT = "testqueue";
    private Destination subjectDestination;
    private Connection connection = null;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;
    private MessageSender messageSender;

    public MessageHandlerImpl() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        messageSender = (MessageSender)context.getBean("messageSender");
    }

    @Override
    public void sendMessageToQueue(Message messageToSend) {
        sendMessageToActiveMqUsingSpring(messageToSend);
    }

    @Override
    public Message receiverMessageFromQueue() {
        return receiverMessageFromActiveMq();
    }


    private void connectToActiveMqServer() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_SERVER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
    }

    private void connectToActiveMqSession() throws JMSException {
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void createActiveMqSubjectDestination() throws JMSException {
        if(subjectDestination == null) {
            subjectDestination = session.createQueue(ACTIVE_MQ_SUBJECT);
        }
    }

    private void createActiveMqProducer() throws JMSException {
        producer = session.createProducer(subjectDestination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
    }

    private void disconnectFromActiveMqServer() throws JMSException {
        connection.close();
    }

    private void disconnectActiveMqSession() throws JMSException {
        session.close();
    }

    private void sendMessageToActiveMqUsingSpring(Message messageToSend) {
        messageSender.send(messageToSend);
    }

    private void sendMessageToActiveMq(Message messageToSend) {
        try {
            connectToActiveMqServer();
            connectToActiveMqSession();
            createActiveMqSubjectDestination();
            createActiveMqProducer();
            send(messageToSend);
            disconnectActiveMqSession();
            disconnectFromActiveMqServer();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    private void send(Message messageToSend) throws Exception {
        ObjectMessage message = session.createObjectMessage(messageToSend);
        producer.send(message);
    }

    private Message receiverMessageFromActiveMq() {
        Message message = null;
        try {
            connectToActiveMqServer();
            connectToActiveMqSession();
            createActiveMqSubjectDestination();
            createActiveMqConsumer();
            message = receive();
            destroyActiveMqConsumer();
            disconnectActiveMqSession();
            disconnectFromActiveMqServer();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
        return message;
    }

    private Message receive() throws JMSException {
        ObjectMessage rawMessage = (ObjectMessage) consumer.receive();
        Message message = (Message) rawMessage.getObject();
        return message;
    }

    private void createActiveMqConsumer() throws JMSException {
        consumer = session.createConsumer(subjectDestination);
    }

    private void destroyActiveMqConsumer() throws JMSException {
        consumer.close();
    }
}

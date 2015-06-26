package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

public class MessageReceiver {

    private JmsTemplate jmsTemplate;
    private ActiveMQQueue destination;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {

        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(ActiveMQQueue destination) {
        this.destination = destination;
    }

    public Message receive() {
        Message message = null;
        String queueName = null;
        try {
            queueName = destination.getQueueName();
            javax.jms.Message receivedMessage = jmsTemplate.receive(queueName);
            ObjectMessage receivedObject = (ObjectMessage) receivedMessage;
            message = (Message) receivedObject.getObject();
        } catch (JMSException e) {
            throw new MessageReceiverException("Can't receive message from queue: " + queueName, e);
        }
        return message;
    }
}


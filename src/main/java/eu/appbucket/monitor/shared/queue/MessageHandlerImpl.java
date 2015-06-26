package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MessageHandlerImpl implements MessageHandler {

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;

    public MessageHandlerImpl() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        messageSender = (MessageSender)context.getBean("messageSender");
        messageReceiver = (MessageReceiver)context.getBean("messageReceiver");
    }

    @Override
    public void sendMessageToQueue(Message messageToSend) {
        messageSender.send(messageToSend);
    }

    @Override
    public Message receiverMessageFromQueue() {
        return messageReceiver.receive();
    }
}

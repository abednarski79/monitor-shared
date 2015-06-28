package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageHandlerImpl implements MessageHandler {

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;

    public MessageHandlerImpl() {
        messageSender = lookupMessageSender();
        messageReceiver = lookupMessageReceiver();
    }

    protected MessageSender lookupMessageSender() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        return (MessageSender)context.getBean("messageSender");
    }

    protected MessageReceiver lookupMessageReceiver() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        return (MessageReceiver)context.getBean("messageReceiver");
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

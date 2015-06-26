package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;

import javax.jms.JMSException;

public interface MessageHandler {

    void sendMessageToQueue(Message messageToSend);
    Message receiverMessageFromQueue();
}

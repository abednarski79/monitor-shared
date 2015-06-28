package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;

public interface MessageHandler {

    void sendMessageToQueue(Message messageToSend);
    Message receiverMessageFromQueue();
}

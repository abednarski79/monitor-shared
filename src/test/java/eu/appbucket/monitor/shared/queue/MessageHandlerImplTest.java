package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;
import eu.appbucket.monitor.shared.pojo.MessageType;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageHandlerImplTest {

    private MessageHandlerImpl handler;
    private final Mockery context = new JUnit4Mockery();
    private MessageReceiver messageReceiverMock;
    private MessageSender messageSenderMock;

    @Before
    public void setup() {
        messageReceiverMock = context.mock(MessageReceiver.class);
        messageSenderMock = context.mock(MessageSender.class);
        handler = new MessageHandlerImpl() {
            @Override
            protected MessageSender lookupMessageSender() {
                return messageSenderMock;
            }
            @Override
            protected MessageReceiver lookupMessageReceiver() {
                return messageReceiverMock;
            }
        };
    }

    @Test
    public void testSendMessage() {
        final Message messageToSend = new Message(1, MessageType.INSERT);
        context.checking(new Expectations() {
            {
                exactly(1).of(messageSenderMock).send(messageToSend);
            }
        });
        handler.sendMessageToQueue(messageToSend);
    }

    @Test
    public void testReceiveMessage() {
        final Message expectedMessageReceived = new Message(2, MessageType.DELETE);
        context.checking(new Expectations() {
            {
                exactly(1).of(messageReceiverMock).receive();
                will(returnValue(expectedMessageReceived));
            }
        });
        Message actualMessageReceived = handler.receiverMessageFromQueue();
        Assert.assertEquals(expectedMessageReceived.getId(), actualMessageReceived.getId());
        Assert.assertEquals(expectedMessageReceived.getOperation(), actualMessageReceived.getOperation());
    }
}

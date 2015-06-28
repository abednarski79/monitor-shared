package eu.appbucket.monitor.shared.queue;

import org.springframework.jms.core.JmsTemplate;

public class MessageSenderImpl implements MessageSender {

    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(final Object Object) {
        jmsTemplate.convertAndSend(Object);
    }
}


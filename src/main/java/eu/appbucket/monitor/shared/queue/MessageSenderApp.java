package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;
import eu.appbucket.monitor.shared.pojo.MessageType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageSenderApp {
    public static void main(String[] args) {
        System.out.println("Test 1 starting.");
        Date startTime = new Date();
        for(int i = 0; i < 1; i++) {
            codeToRun1();
        }
        Date endTime = new Date();
        System.out.println("Test 1 finished in:" + ((endTime.getTime()-startTime.getTime())/1000));

        System.out.println("Test 2 starting.");
        startTime = new Date();
        for(int i = 0; i < 1; i++) {
            codeToRun2();
        }
        endTime = new Date();
        System.out.println("Test 2 finished in:" + ((endTime.getTime()-startTime.getTime())/1000));
    }

    public static void codeToRun1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

        MessageSender messageSender = (MessageSender) context.getBean("messageSender");

        Message pojo = new Message(1, MessageType.INSERT);

        messageSender.send(pojo);

        //System.out.println("Message Send to Jms Queue:- " + pojo) ;
    }

    public static void codeToRun2() {
        MessageHandler handler = new MessageHandlerImpl();

        Message pojo = new Message(1, MessageType.INSERT);
        handler.sendMessageToQueue(pojo);

        //System.out.println("Message Send to Jms Queue:- " + pojo) ;
    }

}

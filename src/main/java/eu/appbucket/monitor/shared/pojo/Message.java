package eu.appbucket.monitor.shared.pojo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

    private int id;
    private MessageType operation;
    private long timeStamp;

    public Message(int id, MessageType operation) {
        this.timeStamp = new Date().getTime();
        this.id = id;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public MessageType getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", time stamp=" + timeStamp +
                ", operation=" + operation +
                '}';
    }
}

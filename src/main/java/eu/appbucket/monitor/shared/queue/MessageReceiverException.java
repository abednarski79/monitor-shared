package eu.appbucket.monitor.shared.queue;

public class MessageReceiverException extends RuntimeException {

    public MessageReceiverException (String message, Throwable cause) {
        super(message, cause);
    }
}

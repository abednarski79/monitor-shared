package eu.appbucket.monitor.shared.queue;

import eu.appbucket.monitor.shared.pojo.Message;

public interface MessageReceiver {
    Message receive();
}

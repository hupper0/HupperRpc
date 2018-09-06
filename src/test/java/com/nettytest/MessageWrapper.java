package com.nettytest;

import java.io.Serializable;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:35
 */
public class MessageWrapper implements Serializable {
    private long timestamp;
    private Message message;

    public MessageWrapper(Message message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

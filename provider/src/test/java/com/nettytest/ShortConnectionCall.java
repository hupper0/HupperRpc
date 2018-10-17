package com.nettytest;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午8:21
 */
public class ShortConnectionCall extends AbstractCall {

    private static final Logger logger = LoggerFactory.getLogger(ShortConnectionCall.class);


    public ShortConnectionCall(String host, int port) throws InterruptedException {
        super(host, port);
    }

    @Override
    public ChannelHandler getChannelHandler() {
        ShortConnCallHandler shortConnCallHandler = new ShortConnCallHandler(this);
        HandlerInitializer handlerInitializer = new HandlerInitializer(shortConnCallHandler);
        return handlerInitializer;
    }

    public Message call(Message message) throws InterruptedException {
        this.setSendMessage(message);
        ChannelFuture channelFuture = super.start();
        channelFuture.channel().closeFuture().sync();
        channelFuture.channel().close();
        if (this.getReceivedMessageWrapper() != null) {
            return this.getReceivedMessageWrapper().getMessage();
        } else {
            return null;
        }
    }

}

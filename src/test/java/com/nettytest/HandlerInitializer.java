package com.nettytest;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:32
 */

public class HandlerInitializer extends ChannelInitializer<SocketChannel> {

    SimpleChannelInboundHandler<MessageWrapper> processHandler;


    private LogLevel logType = LogLevel.DEBUG;

    public HandlerInitializer() {
    }

    public HandlerInitializer(SimpleChannelInboundHandler<MessageWrapper> processHandler) {
        this.processHandler = processHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LoggingHandler(logType));
//        ch.pipeline().addLast(new MessageEncoder(RpcSettings.getSerializeClass().newInstance()));
        ch.pipeline().addLast(new MessageDecoder(RpcSettings.getSerializeClass().newInstance()));
        ch.pipeline().addLast(new MessageEncoder(RpcSettings.getSerializeClass().newInstance()));

        if (processHandler != null) {
            ch.pipeline().addLast(processHandler);
        }
    }
}
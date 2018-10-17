package com.nettytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午8:20
 */
abstract public class AbstractCall {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCall.class);

    private static int threadNumber = Runtime.getRuntime().availableProcessors() * 2;
    private static EventLoopGroup eventLoopGroup;
    private static AtomicInteger eventLoopGroupCount = new AtomicInteger(0);

    protected SocketChannel socketChannel;
    private Message sendMessage;
    private MessageWrapper receivedMessageWrapper;

    protected String host;
    protected int port;

    public AbstractCall(String host, int port) throws InterruptedException {
        this.host = host;
        this.port = port;
    }

    abstract public ChannelHandler getChannelHandler();

    /**
     * 开启对服务端的socket连接
     *
     * @return
     * @throws InterruptedException
     */
    public ChannelFuture start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.group(getEventLoopGroup());
        bootstrap.remoteAddress(host, port);
        bootstrap.handler(this.getChannelHandler());
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
        if (channelFuture.isSuccess()) {
            socketChannel = (SocketChannel) channelFuture.channel();
        }
        return channelFuture;
    }

    private static EventLoopGroup getEventLoopGroup() {
        if (null == eventLoopGroup) {
            eventLoopGroup = new NioEventLoopGroup(threadNumber);
        }

        eventLoopGroupCount.getAndIncrement();

        return eventLoopGroup;
    }

    public static void shutdownEventLoopGroup() throws InterruptedException {
        if (eventLoopGroupCount.get() > 0) {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public Message getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(Message sendMessage) {
        this.sendMessage = sendMessage;
    }

    public MessageWrapper getReceivedMessageWrapper() {
        return receivedMessageWrapper;
    }

    public void setReceivedMessageWrapper(MessageWrapper receivedMessageWrapper) {
        this.receivedMessageWrapper = receivedMessageWrapper;
    }

}
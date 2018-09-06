package com.nettytest;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.BindException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:31
 */
public class Listener {

    private static Logger logger = LoggerFactory.getLogger(Listener.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture channelFuture;
    private ListenerHandler listenerHandler;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    private int port;
    private int backlog = 1024;
    private int threadNumber = 20;
    private boolean retryPort = false;

    public Listener(int port, ListenerHandler listenerHandler) {
        this.port = port;
        this.listenerHandler = listenerHandler;
    }

    public Listener(int port, ListenerHandler listenerHandler, boolean retryPort) {
        this.port = port;
        this.listenerHandler = listenerHandler;
        this.retryPort = retryPort;
    }

    public Listener(int port, int backlog, ListenerHandler listenerHandler) {
        this.port = port;
        this.backlog = backlog;
        this.listenerHandler = listenerHandler;
    }

    public void start() throws InterruptedException {
        this.bossGroup = new NioEventLoopGroup(4);
        this.workerGroup = new NioEventLoopGroup(24);
        try {
            final ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, backlog)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new HandlerInitializer(listenerHandler));
            Thread startThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        this.connect();
                    } catch (BindException e) {
                        if (retryPort) {
//                            port = HttpUtils.freePort();
                            try {
                                this.connect();
                            } catch (BindException | InterruptedException e1) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            throw new RuntimeException(e);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                private void connect() throws BindException, InterruptedException {
                    channelFuture = bootstrap.bind(port).sync();
                    channelFuture.channel().closeFuture().sync();
                }
            });
            startThread.setDaemon(false);
            startThread.start();
            isRunning.set(true);
        } catch (Exception e) {
            logger.error("start listener fail: {}", e.getMessage());
            e.printStackTrace();
            stop();
        }
    }

    public void stop() throws InterruptedException {
        if (bossGroup != null && !bossGroup.isShutdown()) {
            bossGroup.shutdownGracefully().sync();
        }
        if (workerGroup != null && !workerGroup.isShutdown()) {
            workerGroup.shutdownGracefully().sync();
        }
        isRunning.set(false);
    }

    public void await(long intervalMills) throws InterruptedException {
        while (isRunning.get()) {
            Thread.sleep(intervalMills);
        }
    }
}

package com.nettytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:43
 */

abstract public class ListenerHandler extends SimpleChannelInboundHandler<MessageWrapper> {
    static final Logger logger = LoggerFactory.getLogger(ListenerHandler.class);

    public abstract Message handle(Message msg, ChannelHandlerContext ctx);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageWrapper msg) {
        Message response = null;
        try {
            response = handle(msg.getMessage(), ctx);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("handle fail: {}", e.toString());
        }
        ctx.writeAndFlush(new MessageWrapper(response));
    }


    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("channel inactive: {}", ctx.channel().remoteAddress());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("channel fail: {}", cause.toString());
        ctx.close();
    }

}
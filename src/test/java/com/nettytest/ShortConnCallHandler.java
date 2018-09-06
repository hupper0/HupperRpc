package com.nettytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午8:22
 */
public class ShortConnCallHandler extends SimpleChannelInboundHandler<MessageWrapper> {

    private ShortConnectionCall shortConnCall;

    public ShortConnCallHandler() {
    }


    public ShortConnCallHandler(ShortConnectionCall shortConnCall) {
        this.shortConnCall = shortConnCall;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageWrapper msg) throws Exception {
        shortConnCall.setReceivedMessageWrapper(msg);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new MessageWrapper(shortConnCall.getSendMessage()));

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
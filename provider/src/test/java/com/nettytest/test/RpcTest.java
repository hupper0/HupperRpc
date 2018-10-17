package com.nettytest.test;

import com.nettytest.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午8:14
 */
public class RpcTest {

    public static void main(String[]args) throws Exception{

        RpcTest rpcTest = new RpcTest();
        rpcTest.shortConnectionCallTest();

    }


    public  void shortConnectionCallTest() throws Exception {
        int port = 8089;
        Listener testListener = new Listener(port, new TestListenerHandler());
        testListener.start();
        Thread.sleep(500);

        List<String> names = new ArrayList<>();
        names.add("1");
        names.add("我爱你嘻嘻嘻嘻嘻");
        Message request = new Message();
        request.setAction(Action.REGISTER);
        request.set(MessageField.IP,"127.0.0.1");
//        request.set(MessageField.STATUS, TaskStatus.KILLED);
        request.set(MessageField.PORT, 4223);
        request.set(MessageField.RUNNING_TASK, names);

        ShortConnectionCall caller = new ShortConnectionCall("127.0.0.1", port);
        Message res = caller.call(request);
        res.toString();
        System.out.printf("ShortConnectionCall resive msg:  %s", res);
        System.out.println();
        testListener.stop();
    }


    @ChannelHandler.Sharable
    public static class TestListenerHandler extends ListenerHandler {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, MessageWrapper msg)   {
            Message response = handle(msg.getMessage(), ctx);
            MessageWrapper messageWrapper = new MessageWrapper(response);
            ctx.writeAndFlush(messageWrapper);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//            logger.error("channel fail: {}", cause.toString());
            cause.printStackTrace();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
        @Override
        public Message handle(Message msg, ChannelHandlerContext ctx) {
            System.out.println("************************");
            System.out.println("receive message: " + msg);
            msg.set(MessageField.PORT, 8223);
            return msg;
        }
    }
}

package com.nettytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:35
 */
public class MessageEncoder extends MessageToByteEncoder<MessageWrapper> {
//    static final Logger logger = LoggerFactory.getLogger(MessageEncoder.class);

    Serialize serialize;

    public MessageEncoder(Serialize serialize) {
        this.serialize = serialize;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageWrapper msg, ByteBuf out) throws Exception {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            serialize.serialize(byteArrayOutputStream, msg);
            byte[] body = byteArrayOutputStream.toByteArray();
            int dataLength = body.length;
            out.writeInt(dataLength);
            out.writeBytes(body);
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("encode fail: {}, {}", msg.toString(), e.getMessage());
        }

    }
}
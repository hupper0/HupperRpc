package com.nettytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:34
 */

public class MessageDecoder extends ByteToMessageDecoder {
    static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);
    final int MESSAGE_LENGTH_HOLDER = 4;

    Serialize serialize;

    public MessageDecoder(Serialize serialize) {
        this.serialize = serialize;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < MESSAGE_LENGTH_HOLDER) {
            return;
        }

        in.markReaderIndex();
        int messageLength = in.readInt();

        if (messageLength < 0) {
            ctx.close();
        }

        if (in.readableBytes() < messageLength) {
            in.resetReaderIndex();
            return;
        } else {
            byte[] messageBody = new byte[messageLength];
            in.readBytes(messageBody);

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(messageBody)) {
                Object obj = serialize.deserialize(byteArrayInputStream);
                out.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("decode fail: {}", e.toString());

            }
        }
    }
}


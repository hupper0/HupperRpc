package com.nettytest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:34
 */
abstract public class Serialize {
    abstract public void serialize(OutputStream output, Object object) throws IOException;

    abstract public Object deserialize(InputStream input) throws IOException;
}


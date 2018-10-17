package client.proxy;

import client.RPCFuture;

/**
 * Created by lvhongpeng on 2016/3/16.
 */
public interface IAsyncObjectProxy {
    public RPCFuture call(String funcName, Object... args);
}
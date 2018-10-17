package com.hupper.rpc.client;

/**
 * @author lvhongpeng
 */
public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);

}

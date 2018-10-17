package com.nettytest;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:39
 */
public class RpcSettings {
    static Class<? extends Serialize> serializeClass = HessianSerialize.class;


    public static Class<? extends Serialize> getSerializeClass() {
        return serializeClass;
    }

    public static void setSerializeClass(Class<? extends Serialize> serializeClass) {
        RpcSettings.serializeClass = serializeClass;
    }
}

package com.hupper.rpc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午10:07
 */
public class SyncTest extends AbstractQueuedSynchronizer {

    public static void main(String args[])  {
        SyncTest syncTest =  new SyncTest();
        syncTest.acquire(-1);
        System.out.println("xxxxxxxxx");
    }

    private static final long serialVersionUID = 1L;

    //future status
    private final int done = 1;
    private final int pending = 0;

    @Override
    protected boolean tryAcquire(int arg) {
        return getState() == done;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if (getState() == pending) {
            if (compareAndSetState(pending, done)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isDone() {
        getState();
        return getState() == done;
    }
}

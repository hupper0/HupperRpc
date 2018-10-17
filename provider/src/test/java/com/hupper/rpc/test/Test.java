package com.hupper.rpc.test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author lhp@meitu.com
 * @date 2018/7/3 下午4:11
 */
public class Test {


    public static void main(String args[]) {

        Sync syncTest =  new Sync();
        syncTest.acquire(-100);
        System.out.println("xxxxxxxxx");
        System.out.println("xxxxxxxxx");
        System.out.println("xxxxxxxxx");

//
//            Sync sync = new Sync();
//            Thread t = new Thread(){
//                @Override
//                public void run() {
//                    super.run();
//                    System.out.print("1xxxxxxxxx");
//                    sync.acquire(-1);
//                    System.out.print("2xxxxxxxxx");
//                }
//            };
//
//
//            t.start();
//        Thread.sleep(1000 * 10);
//        sync.release(1);
//
////
////        t.interrupt();
//


//        System.out.print("n="+n);

//        try {
//            Sync sync = new Sync();
//            MyThread thread = new MyThread();
//            thread.start();
//            Thread.sleep(20);//modify 2000 to 20
//            thread.interrupt();//请求中断MyThread线程
//        } catch (InterruptedException e) {
//            System.out.println("main catch");
//            e.printStackTrace();
//        }
//        System.out.println("end!");
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {

            }catch (Exception e){
                System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
                e.printStackTrace();
            }
        }
    }

    static class Sync extends AbstractQueuedSynchronizer {

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

}

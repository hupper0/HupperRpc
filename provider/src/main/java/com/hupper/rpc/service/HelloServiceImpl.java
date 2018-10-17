package com.hupper.rpc.service;

import common.RpcService;

/**
 * @author hupper
 * @date 2018/9/29
 */

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String name) {
        try {
            System.out.println("*********start impl");
            Thread.sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*********end impl");
        return "Hello! " + name;
    }
}

package com.hupper.rpc.test.server;

import com.hupper.rpc.test.client.HelloService;
import com.hupper.rpc.test.client.Person;
import common.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl(){

    }

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

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}

package com.hupper;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author hupper
 */
@SpringBootApplication
@EnableCaching
public class RpcBootstrap implements ServletContextListener {

    public static void main(String[] args) {
//        new AnnotationConfigApplicationContext("com.nettyrpc");
        // 启动SpringBoot任务
        new SpringApplicationBuilder(RpcBootstrap.class)
                .run(args);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

package com.learnmultithreading.service;

import java.util.concurrent.CompletableFuture;

import static com.learnmultithreading.util.CommonUtil.delay;
import static com.learnmultithreading.util.LoggerUtil.log;

public class HelloWorldService {

    public  String helloWorld() {
        delay(2000);
        log("inside helloWorld");
        return "hello world";
    }

    public  String hello() {
        delay(2000);
        log("inside hello");
        return "hello";
    }

    public  String world() {
        delay(3000);
        log("inside world");
        return " world!";
    }

    public CompletableFuture<String> worldFuture(String input) {
        return CompletableFuture.supplyAsync(()->{
            delay(1000);
            return input+" world!";
        });
    }

}

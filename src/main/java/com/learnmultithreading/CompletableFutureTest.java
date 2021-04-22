package com.learnmultithreading;

import com.learnmultithreading.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnmultithreading.util.CommonUtil.*;
import static com.learnmultithreading.util.LoggerUtil.log;

public class CompletableFutureTest {

    public static void main(String[] args) {

        HelloWorldService helloWorldService = new HelloWorldService();

        CompletableFuture.
                supplyAsync(helloWorldService::helloWorld).
                thenApply(String::toUpperCase).
                thenAccept(result -> log("Result ::"+ result)).join();

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);

        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);

        CompletableFuture<String> hiCompletableFUture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hello from";
        });

                hello.
                thenCombine(world, (h, w) -> h + w).
                thenCombine(hiCompletableFUture, (p, v) -> p+v).
                thenApply(String::toUpperCase).join();

        timeTaken();

        stopWatchReset();


        startTimer();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.
                supplyAsync(helloWorldService::hello).
                thenCompose(helloWorldService::worldFuture);
        System.out.println(stringCompletableFuture.join());
        timeTaken();
        log("Done");
        //delay(3000);


    }
}

package com.learnmultithreading.forkjoin;

import com.learnmultithreading.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.learnmultithreading.util.CommonUtil.delay;
import static com.learnmultithreading.util.CommonUtil.stopWatch;
import static com.learnmultithreading.util.LoggerUtil.log;

public class ForkJoinUsingRecursive extends RecursiveTask<List<String>> {

    private List<String> inputList;

    public ForkJoinUsingRecursive(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

        stopWatch.start();
        //List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        log("names : "+ names);

        ForkJoinPool forkJoinPool =new ForkJoinPool();
        ForkJoinUsingRecursive forkJoinUsingRecursive = new ForkJoinUsingRecursive(names);
        List<String> invoke = forkJoinPool.invoke(forkJoinUsingRecursive);


//        names.forEach((name)->{
//            String newValue = addNameLengthTransform(name);
//            resultList.add(newValue);
//        });
        stopWatch.stop();
        log("Final Result : "+ invoke);
        log("Total Time Taken : "+ stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    @Override
    protected List<String> compute() {
        if(inputList.size()<=1){
            List<String> resultList= new ArrayList<>();
            inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
            return resultList;

        }
        int size = inputList.size() / 2;
        ForkJoinTask<List<String>> fork = new ForkJoinUsingRecursive(inputList.subList(0, size)).fork();
        inputList = inputList.subList(size,inputList.size());
        List<String> rightResult= compute();
        List<String> leftResult = fork.join();
        leftResult.addAll(rightResult);

        return leftResult;
    }
}

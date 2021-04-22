package com.learnmultithreading;

import com.learnmultithreading.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnmultithreading.util.CommonUtil.*;
import static com.learnmultithreading.util.LoggerUtil.log;

public class Parallelstream {

    public List<String> StringtransFrom(List<String> namesList){
       //return  namesList.stream().map(this::addNameLengthTransform).collect(Collectors.toList());
       return  namesList.
               parallelStream().
               map(this::addNameLengthTransform).
               //sequential().
               collect(Collectors.toList());
    }

    public List<String> stringtransFromLowerCase(List<String> namesList){
        return  namesList.
                parallelStream().
                map(String::toLowerCase).
                collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        Parallelstream parallelstream = new Parallelstream();
        startTimer();
        List<String> stringList = parallelstream.StringtransFrom(namesList);
        log("StringList : "+ stringList);
        timeTaken();

        stopWatchReset();

        startTimer();
        List<String> lowerCase = parallelstream.stringtransFromLowerCase(namesList);
        log("StringList : "+ lowerCase);
        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}

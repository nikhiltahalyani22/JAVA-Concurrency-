package io.nikks;

import java.util.concurrent.*;

public class J_CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable r =new Runnable() {
            @Override
            public void run() {
                System.out.println("Printed from Runnable");
            }
        };

        Callable<String> callable =new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Printed from callable");
                Thread.sleep(2000);
                return "Value from Callable";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //You can create a thread any give it something to run and when its complete it assign it to Future<> object
        Future<String> ret = executorService.submit(callable);

        //then you can do some stuff while you are waiting for the result
        System.out.println("Executing things in the main thread");
        System.out.println("Executing more things in the main thread");

        //Once you get the result you take then in a primitive for any other object and call get
        System.out.println(ret);
        String retString = ret.get();
        System.out.println(retString);
    }
}

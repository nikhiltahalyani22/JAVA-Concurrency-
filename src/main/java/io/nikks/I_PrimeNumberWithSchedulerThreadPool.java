package io.nikks;

import java.util.Scanner;
import java.util.concurrent.*;

public class I_PrimeNumberWithSchedulerThreadPool {
    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        Runnable reporterRunnable = ()->{
            System.out.println("Running report");
            System.out.println("Active threads: "+executorService.getActiveCount());
            System.out.println("Completed threads: "+ executorService.getCompletedTaskCount());
        };

        scheduledExecutor.scheduleAtFixedRate(reporterRunnable, 1,5, TimeUnit.SECONDS);

        //ExecutorService executorService = Executors.newFixedThreadPool(3);


        while (true){
            Scanner sc =new Scanner(System.in);
            System.out.print("Enter n : ");
            int n= sc.nextInt();
            if(n==0)break;
            Runnable r= new Runnable() {
                @Override
                public void run() {
                   int number=PrimeNumberUtil.calculatePrime(n);
                    System.out.println("Value of "+n +"th prime = " + number);
                }
            };
            executorService.execute(r);
        }

    }
}
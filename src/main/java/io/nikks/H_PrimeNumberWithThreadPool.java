package io.nikks;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class H_PrimeNumberWithThreadPool {
    public static void main(String[] args) {
        // Thread Pool of 3 thread
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //Cached Thread Pool
        //ExecutorService executorService = Executors.newCachedThreadPool();

        //Single Thread Pool
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

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
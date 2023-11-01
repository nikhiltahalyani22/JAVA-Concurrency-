package io.nikks;


import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L_PrimeNumberWithCompletableFuture {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter n : ");
            int n = sc.nextInt();
            if (n == 0) break;

            //give it something to execute in a thread
            CompletableFuture.supplyAsync(()-> PrimeNumberUtil.calculatePrime(n))
                    //once it's done run this
                    .thenAccept((Integer retValue)-> System.out.println("The Result is " + retValue));

            /*
            Give you own thread pool
            CompletableFuture.supplyAsync(()-> PrimeNumberUtil.calculatePrime(n),executorService)
                    //once it's done run this
                    .thenAccept((Integer retValue)-> System.out.println("The Result is " + retValue));
            CompletableFuture.supplyAsync(()-> PrimeNumberUtil.calculatePrime(n),Executors.newFixedThreadPool(23))
                    //once it's done run this
                    .thenAccept((Integer retValue)-> System.out.println("The Result is " + retValue));
*/


            
        }



    }
}
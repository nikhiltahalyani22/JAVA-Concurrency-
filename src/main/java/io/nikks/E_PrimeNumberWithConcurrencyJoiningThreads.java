package io.nikks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E_PrimeNumberWithConcurrencyJoiningThreads {

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();

        Runnable statusReporter = () -> {
            try {
                while (true) {
                    Thread.sleep(5000);
                    printThreads(threads);
                }
            } catch (InterruptedException e) {
                System.out.println("Interpreted ");
            }
        };
        Thread reporterThread = new Thread(statusReporter);
        reporterThread.start();


        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter n : ");
            int n = sc.nextInt();
            if (n == 0) {
                reporterThread.interrupt();
                try {
                    System.out.println("Waiting for all threads to finish ....");
                    waitForThreads(threads);
                    System.out.println("Done with the application. "+threads.size()+" prime number calculated");
                }catch (InterruptedException e){
                    System.out.println("Got interrupted when waiting for threads. Quitting...");
                }
                break;
            }
            Runnable r = () -> {
                int number = PrimeNumberUtil.calculatePrime(n);
                System.out.println("Value of " + n + "th prime = " + number);
            };
            Thread t = new Thread(r);
            threads.add(t);
            t.start();
        }

    }

    private static void waitForThreads(List<Thread> threads) throws InterruptedException{
        for (Thread thread:threads) {
            thread.join();
        }
    }

    private static void printThreads(List<Thread> threads) {
        System.out.print("Thread Status: ");
        threads.forEach(thread -> System.out.print(thread.getState() + " "));
        System.out.println();
    }
}
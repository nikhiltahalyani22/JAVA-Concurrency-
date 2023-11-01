package io.nikks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D_PrimeNumberWithConcurrencyHoldingThreads {
    @SuppressWarnings("DuplicatedCode")
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
        reporterThread.setDaemon(true);
        reporterThread.start();


        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter n : ");
            int n = sc.nextInt();
            if (n == 0) break;
            Runnable r = () -> {
                int number = PrimeNumberUtil.calculatePrime(n);
                System.out.println("Value of " + n + "th prime = " + number);
            };
            Thread t = new Thread(r);
            threads.add(t);
            t.setDaemon(true);
            t.start();
        }

    }

    private static void printThreads(List<Thread> threads) {
        System.out.print("Thread Status: ");
        threads.forEach(thread -> System.out.print(thread.getState() + " "));
        System.out.println();
    }
}
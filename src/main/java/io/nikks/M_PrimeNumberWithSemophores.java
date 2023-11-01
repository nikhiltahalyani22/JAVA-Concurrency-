package io.nikks;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class M_PrimeNumberWithSemophores {
    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(3);
        //If you want guarantee first in first out of waiting thread
        //Semaphore semaphore = new Semaphore(3,true);

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter n : ");
            int n = sc.nextInt();
            if (n == 0) break;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        //semaphore.acquireUninterruptibly();
                        semaphore.acquire();
                        int number = PrimeNumberUtil.calculatePrime(n);
                        System.out.println("Value of " + n + "th prime = " + number);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                        ;
                    } finally {
                        semaphore.release();
                    }
                }
            };
            Thread t = new Thread(r);
            t.start();
        }

    }
}
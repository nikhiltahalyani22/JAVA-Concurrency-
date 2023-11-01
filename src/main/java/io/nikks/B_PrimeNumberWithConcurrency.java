package io.nikks;

import java.util.Scanner;

public class B_PrimeNumberWithConcurrency {
    public static void main(String[] args) {


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
            Thread t= new Thread(r);
            t.start();
        }

    }
}
package io.nikks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class K_PrimeNumberWithConcurrencyCallable  {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<Future<Integer>> futures =new ArrayList<>();

        ExecutorService executorService= Executors.newCachedThreadPool();

        while (true){
            Scanner sc =new Scanner(System.in);
            System.out.print("Enter n : ");
            int n= sc.nextInt();
            if(n==0)break;
            Callable<Integer> primeNumberCallable= new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return PrimeNumberUtil.calculatePrime(n);
                }
            };


            Future<Integer> submit = executorService.submit(primeNumberCallable);
            futures.add(submit);
            System.out.println("---------");


/*            Iterator<Future<Integer>> iterator = futures.iterator();

            while (iterator.hasNext()) {
                Future<Integer> f =iterator.next();
                if (f.isDone()) {
                    System.out.println("The result is " + f.get());
                    iterator.remove();
                }
            }*/
            //this will block the execution
            //System.out.println("The result is "+ submit.get());


        }
        Iterator<Future<Integer>> iterator = futures.iterator();

        while (iterator.hasNext()) {
            Future<Integer> f =iterator.next();
            if (f.isDone()) {
                System.out.println("The result is " + f.get());
                iterator.remove();
            }
        }

    }
}
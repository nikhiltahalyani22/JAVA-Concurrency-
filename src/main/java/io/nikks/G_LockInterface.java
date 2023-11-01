package io.nikks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter1 implements Runnable{
    private int value=0;
    //define a lock object
    private Lock l = new ReentrantLock();


    public void increment(){
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        value++;
    }

    public void decrement(){ value --;}

    public int getValue(){return value;}

    //Synchronized
    @Override
    public synchronized void run() {
        // acquire a lock
        l.lock();
        try {
            this.increment();
            System.out.println(Thread.currentThread().getName()+ " increment: "+ this.getValue());
            this.decrement();
            System.out.println(Thread.currentThread().getName()+ " decrement: "+ this.getValue());
        }finally {
            // release a lock
            l.unlock();
        }

   }
}

public class G_LockInterface {
    public static void main(String[] args) {
        Counter1 counter1 = new Counter1();
        new Thread(counter1,"One").start();
        new Thread(counter1,"Two").start();
        new Thread(counter1,"Three").start();
        new Thread(counter1,"Four").start();
        Counter1 counter12 = new Counter1();
        new Thread(counter12, "Counter 2 thread").start();

    }
}
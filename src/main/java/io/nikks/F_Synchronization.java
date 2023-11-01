package io.nikks;

public class F_Synchronization {
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

class Counter implements Runnable{
    private int value=0;



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
        //synchronized (this) {
            this.increment();
            System.out.println(Thread.currentThread().getName()+ " increment: "+ this.getValue());
            this.decrement();
            System.out.println(Thread.currentThread().getName()+ " decrement: "+ this.getValue());
       // }
   }
}

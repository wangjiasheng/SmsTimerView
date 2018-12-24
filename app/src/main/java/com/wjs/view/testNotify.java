package com.wjs.view;

public class testNotify {
    public static final void main(String [] args){
        final Object lock=new Object();
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.wait(1000);
                    }
                    System.out.println(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        synchronized (lock){
            lock.notify();
        }
    }
}

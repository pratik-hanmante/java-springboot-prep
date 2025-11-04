package com.example;

class Worker extends Thread {
    private String taskName;

    public Worker(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started by " + Thread.currentThread().getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + " - step " + i);
            try {
                Thread.sleep(500); // simulate some work
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted!");
            }
        }
        System.out.println(taskName + " finished by " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread started: " + Thread.currentThread().getName());

        Worker t1 = new Worker("Task-A");
        Worker t2 = new Worker("Task-B");

        t1.start();
        t2.start();

        try {
            // join() makes the main thread wait until both threads finish
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted!");
        }

        System.out.println("All tasks completed. Exiting main thread.");
    }
}

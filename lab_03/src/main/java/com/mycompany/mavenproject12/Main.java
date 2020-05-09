package com.mycompany.mavenproject12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {

    static Queue queue = new Queue();

    static List<Thread> producers = new ArrayList<>();
    static List<Thread> consumers = new ArrayList<>();
    static BlockingQueue<Item> toProduce = queue.getQueue();
    static BlockingQueue<Item> toConsume = new LinkedBlockingQueue<>(queue.getQueue().size());

    static ExecutorService es = Executors.newFixedThreadPool(7);
    static BlockingQueue<Item> itemsQueue = queue.getQueue();

    //operating on threads
    public static Thread createProducer() {
        Thread t = new Thread(() -> {
            Item item = null;
            while (!toProduce.isEmpty()) {
                item = toProduce.poll();
                if (item != null) {
                    item.produceMe();
                    toConsume.add(item);
                }
            }
        });
        return t;
    }

    public static Thread createConsumer() {
        Thread t = new Thread(() -> {
            Item item = null;
            while (!toConsume.isEmpty() || producers.stream().anyMatch(i -> i.isAlive())) {
                try {
                    item = toConsume.take();
                } catch (Exception e) {
                    System.out.println("Exception in creating consumer");
                }
                if (item != null) {
                    item.consumeMe();
                }
            }
        });
        return t;
    }

    public static void threads() {
        for (int i = 0; i < 4; i++) {
            producers.add(createProducer());
        }
        for (int i = 0; i < 3; i++) {
            consumers.add(createConsumer());
        }

        long start = System.currentTimeMillis();
        for (Thread thread : producers) {
            thread.start();
        }
        for (Thread thread : consumers) {
            thread.start();
        }

        while (producers.stream().anyMatch(i -> i.isAlive())
                || consumers.stream().anyMatch(i -> i.isAlive())) {
            //empty loop
        }
        long stop = System.currentTimeMillis();
        double time = stop - start;
        System.out.println("Time: " + time / 1000 + " seconds");
    }

    //operating on threads pool
    public static void threadsPool() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 7; i++) {
            es.submit(new Producer(itemsQueue.poll()));
        }
        try {
            es.awaitTermination(3, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        double time = stop - start;
        System.out.println("Time: " + time / 1000 + " seconds");
    }
    
    //operating on threads pool using stream API:
    public static void threadsPoolUsingStream(){
        long start = System.currentTimeMillis();
        itemsQueue.stream().parallel().forEach(Item::produceMe);
        itemsQueue.stream().parallel().forEach(Item::consumeMe);
        long stop = System.currentTimeMillis();
        double time = stop - start;
        System.out.println("Time: " + time / 1000 + " seconds");
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //boolean yourTry = true;
        int choice = 0;

        System.out.println("Generator Item'ów. Wybierz wariant:");
        System.out.println("1. Wątki");
        System.out.println("2. Pula wątków");
        System.out.println("3. Pula wątków za pomocą Stream API");
        System.out.println("-------------------------------------------");
        System.out.print("Twój wybór: ");

        //while (yourTry) {
        try {
            choice = Integer.valueOf(br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (choice) {
            case 1:
                threads();
                break;
            case 2:
                threadsPool();
                break;
            case 3:
                threadsPoolUsingStream();
                break;
            default:
                break;
        }
    }

}

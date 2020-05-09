package com.mycompany.mavenproject12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {

    BlockingQueue<Item> queue;

    public Queue() {
        queue = new LinkedBlockingQueue<>(100);
        for (int i = 0; i < 100; i++) {
            queue.add(new Item());
        }
    }

    public BlockingQueue getQueue(){
        return queue;
    }
}

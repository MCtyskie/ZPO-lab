
package com.mycompany.mavenproject12;

import static com.mycompany.mavenproject12.Main.es;
import static com.mycompany.mavenproject12.Main.itemsQueue;

public class Consumer implements Runnable{

    private Item item;
    public Consumer(Item item){
        this.item=item;
    }
    
    @Override
    public void run() {
        if(item!=null){
            item.consumeMe();
            item=itemsQueue.poll();
            es.submit(new Producer(item));
        }
    }
    
}

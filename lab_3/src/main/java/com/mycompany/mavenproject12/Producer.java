
package com.mycompany.mavenproject12;

import static com.mycompany.mavenproject12.Main.es;

public class Producer implements Runnable{

    private Item item;
    public Producer(Item item){
        this.item=item;
    }
    
    @Override
    public void run() {
        if(item!=null){
            item.produceMe();
            es.submit(new Consumer(item));
        }else{
            es.shutdown();
        }
    }
    
}

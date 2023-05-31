package org.example.nsq.task1;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

public class Consumer {
  public static void main(String[] args) {

    NSQLookup lookup = new DefaultNSQLookup();
    lookup.addLookupAddress("localhost", 4161);

    NSQConsumer consumer = new NSQConsumer(
            lookup,"mytopic", "mychannel", nsqMessage -> {
      System.out.println(new String(nsqMessage.getMessage()));
    });

    // Start multiple consumers simultaneously
    for (int i = 1; i <= 5; i++) {
      new Thread(() -> consumer.start()).start();
    }
  }

}

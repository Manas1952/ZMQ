package org.example.nsq.basic;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import com.google.gson.Gson;

public class Consumer2 {
  public static void main(String[] args) {
    NSQLookup lookup = new DefaultNSQLookup();
    lookup.addLookupAddress("localhost", 4161);

    String data = new Gson().toJson(lookup.lookup("topic1"));
    System.out.println("->" + data);

    NSQConsumer consumer = new NSQConsumer(lookup, "topic1", "channel1", nsqMessage -> {

      String message = new String(nsqMessage.getMessage());

      System.out.println("received: " + message);

      nsqMessage.finished();
    });

    consumer.start();
  }
}

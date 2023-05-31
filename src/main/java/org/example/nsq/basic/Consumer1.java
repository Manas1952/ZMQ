package org.example.nsq.basic;

import com.github.brainlag.nsq.NSQConfig;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer1 {



  public static void main(String[] args) {
    NSQLookup lookup = new DefaultNSQLookup();
    lookup.addLookupAddress("localhost", 4161);

    String data = new Gson().toJson(lookup.lookup("topic1"));
    System.out.println("->" + data);

    NSQConfig config = new NSQConfig();
//    config.setMaxInFlight(1);
//    config.setMsgTimeout(1000);

    NSQConsumer consumer = new NSQConsumer(lookup, "topic1", "channel1", nsqMessage -> {

      String message = new String(nsqMessage.getMessage());

      System.out.println("received: " + message + Thread.currentThread().getThreadGroup()+" "+ Thread.currentThread().getName());

      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      try {
//        Thread.sleep(100);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      System.out.println(Thread.activeCount());

      nsqMessage.finished();
    });

//    ExecutorService executorService = Executors.newFixedThreadPool(10);

//    Pool pool = new Pool();
    consumer.setExecutor(Pool.executorService);

    consumer.start();
  }
}

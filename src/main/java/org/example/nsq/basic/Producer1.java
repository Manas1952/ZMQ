package org.example.nsq.basic;

import com.github.brainlag.nsq.NSQConfig;
import com.github.brainlag.nsq.NSQProducer;
public class Producer1 {
  public static void main(String[] args) {
    NSQProducer producer = new NSQProducer().addAddress("localhost", 4150);

    producer.start();

    try {

      for (int i = 0; i < 10000; i++) {

        String message = new String("message from server " + i);

        producer.produce("topic1", message.getBytes());


        Thread.sleep(100);
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
    }

    producer.shutdown();
  }
}

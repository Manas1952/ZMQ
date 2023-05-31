package org.example.nsq.basic;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

public class Producer2 {
  public static void main(String[] args) {
    NSQProducer producer = new NSQProducer().addAddress("localhost", 4150);
    producer.start();

    for (int i = 0; i < 100; i++) {
      try {

        producer.produce("topic1", new String("by Producer2" + i).getBytes());

        Thread.sleep(1000);
      } catch (NSQException e) {
        throw new RuntimeException(e);
      } catch (TimeoutException e) {
        throw new RuntimeException(e);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

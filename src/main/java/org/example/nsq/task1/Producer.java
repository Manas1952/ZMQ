package org.example.nsq.task1;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

public class Producer {
  public static void main(String[] args) throws NSQException, TimeoutException, InterruptedException {
    // Create an NSQ producer
    NSQProducer producer = new NSQProducer()
            .addAddress("localhost", 4150); // Replace with the address of your NSQD instance
            producer.start();

    // Produce 10 messages to the topic
    for (int i = 1; i <= 10; i++) {
      producer.produce("mytopic", ("Message " + i).getBytes());

      Thread.sleep(1000);
    }

    // Close the producer
//    producer.shutdown();
  }
}

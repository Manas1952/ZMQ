package org.example.nsq.basic;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.ServerAddress;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.NSQLookup;
import java.util.Set;

public class Client {
  public static void main(String[] args) {
    NSQConsumer consumer = new NSQConsumer(new NSQLookup() {
      @Override
      public Set<ServerAddress> lookup(String s) {
        return null;
      }

      @Override
      public void addLookupAddress(String s, int i) {

      }
    }, "topic1", "channel1", new NSQMessageCallback() {
      @Override
      public void message(NSQMessage nsqMessage) {
        System.out.println("->" + nsqMessage);
      }
    });
  }
}

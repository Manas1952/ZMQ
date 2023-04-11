package org.example.proxy;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.net.Socket;

public class Proxy {
  public static void main(String[] args) {
    try (ZContext context = new ZContext()) {
      ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);
      frontend.bind("tcp://*:5559");
      frontend.subscribe(ZMQ.SUBSCRIPTION_ALL);

      ZMQ.Socket backend = context.createSocket(SocketType.DEALER);
      backend.bind("tcp://*:5560");

      ZMQ.proxy(frontend, backend, null);
    }
  }
}

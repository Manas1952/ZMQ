package org.example.polling;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Poller {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {

            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            receiver.connect("tcp://localhost:5557");

            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");
            subscriber.subscribe("10001".getBytes(ZMQ.CHARSET));

            ZMQ.Poller poller = context.createPoller(2);
            poller.register(receiver, ZMQ.Poller.POLLIN);
            poller.register(subscriber, ZMQ.Poller.POLLIN);

            while (true) {
                byte[] message;

                poller.poll();
                if (poller.pollin(0)) {
                    message = receiver.recv(0);
                    System.out.println("Processing tasks...");
                }
                else {
                    message = subscriber.recv(0);
                    System.out.println("Processing weather update...");
                }
            }
        }
    }
}

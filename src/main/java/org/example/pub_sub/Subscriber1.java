package org.example.pub_sub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.StringTokenizer;

public class Subscriber1 {
    public static void main(String[] args) throws InterruptedException {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Collecting updates from weather server");
            Thread.sleep(1000);
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");

            subscriber.subscribe("update".getBytes(ZMQ.CHARSET));

            String string = subscriber.recvStr(0).trim();

            System.out.println(string);
        }
    }
}

package org.example.push_pull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Worker {
    public static void main(String[] args) throws InterruptedException {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            receiver.connect("tcp://localhost:5557");

            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);
            sender.connect("tcp://localhost:5558");

            while (!Thread.currentThread().isInterrupted()) {
                String string = new String(receiver.recv(0), ZMQ.CHARSET);

                System.out.flush();
                System.out.println("-> " + string);
                Thread.sleep(500);

                sender.send(string, ZMQ.PAIR);
            }
        }
    }
}

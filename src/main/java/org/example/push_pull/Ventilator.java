package org.example.push_pull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class Ventilator {
    public static void main(String[] args) throws IOException, InterruptedException {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);
            sender.bind("tcp://*:5557");

            ZMQ.Socket sink = context.createSocket(SocketType.PUSH);
            sink.connect("tcp://localhost:5558");

            System.out.println("Press 'Enter' when workers are ready");
            System.in.read();
            System.out.println("Sending tasks to workers");

//            sink.send("0", 0);

            for (int iterator = 0; iterator < 100; iterator++) {

                sender.send(iterator + " <-");
            }

            // waiting to transfer tasks
            Thread.sleep(1000);
        }
    }
}

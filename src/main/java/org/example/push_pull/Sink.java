package org.example.push_pull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Sink {
    public static void main(String[] args) {

        try (ZContext context = new ZContext()) {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            receiver.bind("tcp://*:5558");

            // wait for start of batch
            String string = new String(receiver.recv(0), ZMQ.CHARSET);
//            String string;

            for (int iterator = 0; iterator < 100; iterator++) {

                string = new String(receiver.recv(0), ZMQ.CHARSET);
                System.out.println("received: " + string);
            }

            System.out.println("done");
        }
    }
}

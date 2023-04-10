package org.example.pub_sub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

public class Publisher1 {
    public static void main(String[] args) throws InterruptedException {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");
            publisher.bind("ipc://weather");

            String update = "update sankalp haoi";
            String sankalp = "sankalp";
            String manas = "manas";


//            while (true) {

            // making it sleep so that it send data after tcp connection gets established
            Thread.sleep(5000);
                publisher.send(update, 0);
//                publisher.send(manas, 0);
//                publisher.send(sankalp, 0);
//            }
        }
    }
}

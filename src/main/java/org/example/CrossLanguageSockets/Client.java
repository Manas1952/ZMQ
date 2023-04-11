package org.example.CrossLanguageSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 5789);

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


  }
}

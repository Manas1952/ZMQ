package org.example.nsq.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pool
{
   public static ExecutorService executorService = Executors.newFixedThreadPool(10);
}

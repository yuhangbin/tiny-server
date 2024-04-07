package org.example.benchmark;

import org.example.tcp.client.client.ProtobufClient;
import org.example.tcp.client.client.TcpClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProtoBufTCPServerBenchmark {

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < 100000; i++) {
                executorService.execute(() -> {


                });
            }
        }
    }

    public static class Client implements Runnable{
        @Override
        public void run() {
            TcpClient tcpClient = new ProtobufClient();
            tcpClient.start();
            tcpClient.send()
        }
    }


}

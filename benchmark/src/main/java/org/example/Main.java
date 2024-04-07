package org.example;

import org.example.tcp.client.client.ProtobufClient;
import org.example.tcp.client.client.TcpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception{
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "ulimit -n");
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println("The ulimit -n value is: " + line);
        }

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < 20000; i++) {
                executorService.execute(() -> {
                        TcpClient tcpClient = new ProtobufClient();
                        tcpClient.start();
                });
            }
        } finally {

        }

    }
}
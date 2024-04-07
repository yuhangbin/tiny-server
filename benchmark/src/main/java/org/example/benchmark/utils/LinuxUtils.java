package org.example.benchmark.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LinuxUtils {

    public static String ulimit() {
        String prefix = "The ulimit -n value is: ";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "ulimit -n");
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            if ((line = reader.readLine()) != null) {
                return prefix + line;
            }
        } catch (Throwable e) {

        }
        return prefix + "unknown";
    }
}

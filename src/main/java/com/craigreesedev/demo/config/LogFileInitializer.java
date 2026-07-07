package com.craigreesedev.demo.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogFileInitializer implements CommandLineRunner {
    static final Path LOG_FILE_PATH = Path.of(Paths.get("").toAbsolutePath()+ "/log");

    @Override
    public void run(String... args) throws Exception {
        try {
            if (Files.notExists(LOG_FILE_PATH)) {
                Files.createFile(LOG_FILE_PATH);
            }
        } catch (Exception e) {
            System.out.println("Error creating log file");
        }

        try (BufferedWriter output = new BufferedWriter(new FileWriter(LOG_FILE_PATH.toString(), true))) {
            output.write("timestamp,method_name,execution_time_ms");
            output.newLine();
        } catch (Exception e) {
            System.out.println("Error writing to log file");
        }
    }
}

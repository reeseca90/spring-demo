package com.craigreesedev.demo.config;

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
    }
}

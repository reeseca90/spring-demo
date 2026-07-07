package com.craigreesedev.demo.aspect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitorExecutionTime {
    static final String LOG_FILE_PATH = Paths.get("").toAbsolutePath().toString() + "/log";

    @Around("execution(* com.craigreesedev.demo.user.UserController.*(..))")
    public Object monitorExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();

        try (BufferedWriter output = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            // Write timestamp, method name, and execution time to the log file in CSV format
            output.write("%d,%s,%d".formatted(System.currentTimeMillis(), pjp.getSignature().toShortString(), endTime - startTime));
            output.newLine();
        } catch (Exception e) {
            System.out.println("Error writing to log file");
        }

        return result;
    }
}

package com.craigreesedev.demo.aspect;

import java.io.FileWriter;
import java.nio.file.Paths;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitorExecutionTime {
    final static String logFilePath = Paths.get("").toAbsolutePath().toString() + "/log";

    @Around("execution(* com.craigreesedev.demo.user.UserController.*(..))")
    public Object monitorExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();

        // TODO: update this to be a CSV-style log file with timestamp, method name, execution time
        try (FileWriter output = new FileWriter(logFilePath)) {
            output.write("Execution time (ms): %d".formatted(endTime - startTime));
        } catch (Exception e) {
            System.out.println("Error writing to log file");
        }

        return result;
    }
}

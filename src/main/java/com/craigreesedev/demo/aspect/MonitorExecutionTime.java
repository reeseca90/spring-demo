package com.craigreesedev.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitorExecutionTime {
    @Around("execution(* com.craigreesedev.demo.user.UserController.*(..))")
    public Object monitorExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();

        // TODO: output this in a log file
        System.out.println("Execution time (ms): %d".formatted(endTime - startTime));
        return result;
    }
}

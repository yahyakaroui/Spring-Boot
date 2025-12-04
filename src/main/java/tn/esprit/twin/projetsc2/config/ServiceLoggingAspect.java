package tn.esprit.twin.projetsc2.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Pointcut("within(tn.esprit.twin.projetsc2.services..*)")
    public void serviceLayer() {
        // Pointcut for all service classes
    }

    @Before("serviceLayer()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Calling {} with args {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    @After("serviceLayer()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Finished {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method {} returned {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Method {} threw exception {}", joinPoint.getSignature(), exception.getMessage(), exception);
    }

    @Around("serviceLayer()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsed = System.currentTimeMillis() - start;
            log.info("Execution time for {}: {} ms", joinPoint.getSignature(), elapsed);
        }
    }

}

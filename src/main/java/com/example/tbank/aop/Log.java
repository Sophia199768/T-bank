package com.example.tbank.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Log {

    @Pointcut("@annotation(com.example.tbank.aop.Loggable)")
    public void logExecutionTimeMethod() {
    }

    @Pointcut("@within(com.example.tbank.aop.Loggable)")
    public void logExecutionTimeClass() {
    }

    @Around("logExecutionTimeMethod() || logExecutionTimeClass()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("Method {} in class {} executed in {} ms",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                executionTime);

        return proceed;
    }
}
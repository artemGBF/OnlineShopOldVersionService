package com.gbf.onlineshop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogTimeAspect {

    @Around("@annotation(com.gbf.onlineshop.aspects.annotations.LogTime)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object proceed;
        try {
            proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
        finally {
            Long endTime = System.currentTimeMillis();
            System.out.println(joinPoint.getSignature().getName()+" executes "+(endTime-startTime)+"ms");
        }
    }
}

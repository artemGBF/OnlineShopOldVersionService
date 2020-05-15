package com.gbf.onlineshop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LogArgumentAspect {

    @Pointcut("execution(* com.gbf.onlineshop.controller.*.*(..)) && args(id,..)")
    public void beforeControllerPointcut(Long id) {

    }

    @Before("beforeControllerPointcut(id)")
    public void beforeControllers(JoinPoint joinPoint, Long id) {
        System.out.println(joinPoint.getSignature().getName()+" "+id);
    }
}

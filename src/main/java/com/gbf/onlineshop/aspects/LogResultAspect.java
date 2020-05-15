package com.gbf.onlineshop.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogResultAspect {

    @AfterReturning(value = "execution(* com.gbf.onlineshop.controller.*.*(..))", returning = "result")
    public void logResult(Object result){
        System.out.println(result);
    }
}

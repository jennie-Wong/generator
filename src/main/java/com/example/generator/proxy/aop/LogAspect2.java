package com.example.generator.proxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogAspect2 implements Ordered {
    @Pointcut("within(com.example.generator.proxy.*)")
    public void point() {
    }

    @Before("point()")
    public void before(){
        System.out.println("log before2...");
    }

    @AfterReturning("point()")
    public void afterReturning() { System.out.println("log after returning2..."); }

    @Override
    public int getOrder() {
        return 2;
    }
}

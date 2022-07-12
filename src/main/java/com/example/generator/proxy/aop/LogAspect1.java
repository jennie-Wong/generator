package com.example.generator.proxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

/**
 * 调用拦截器链，使其能够按before -> method -> after -> afterReturning的顺序链式调用
 *
 *
 *
 */
@Component
@Slf4j
@Aspect
public class LogAspect1 implements Ordered {
    @Pointcut("within(com.example.generator.proxy.impl.*)")
    public void point() {
    }

    @Before("point()")
    public void before(){
        System.out.println("log before1...");
    }

    @AfterReturning("point()")
    public void afterReturning() { System.out.println("log after returning1..."); }

    @Override
    public int getOrder() {
        return 1;
    }
}

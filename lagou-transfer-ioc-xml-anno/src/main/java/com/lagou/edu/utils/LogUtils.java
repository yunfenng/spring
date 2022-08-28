package com.lagou.edu.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Jaa
 * @date 2022/1/8 23:34
 */
@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(* com.lagou.edu.service.impl.TransferServiceImpl.*(..))")
    public void pt1() {

    }

    /**
     * 业务逻辑开始执行之前执行
     */
    @Before("pt1()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.println(arg);
        }
        System.out.println("业务逻辑开始执行之前执行...");
    }

    /**
     * 业务逻辑结束时执行(无论异常是否)
     */
    @After("pt1()")
    public void afterMethod() {
        System.out.println("业务逻辑结束时执行...");
    }

    /**
     * 异常时执行
     */
    @AfterThrowing("pt1()")
    public void exceptionMethod() {
        System.out.println("异常时执行...");
    }

    /**
     * 业务逻辑正常时执行
     */
    @AfterReturning(value = "pt1()", returning = "retValue")
    public void successMethod(Object retValue) {
        System.out.println("业务逻辑正常时执行...");
    }


    /**
     * 环绕通知
     */
    // @Around("pt1()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知中的beforeMethod...");
        Object result = null;
        try {
            // 控制原有业务逻辑是否执行
            result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Exception e) {
            System.out.println("环绕通知中的exceptionMethod...");
        } finally {
            System.out.println("环绕通知中的afterMethod...");
        }
        return result;
    }
}

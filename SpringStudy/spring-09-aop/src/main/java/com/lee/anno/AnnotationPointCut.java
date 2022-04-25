package com.lee.anno;

// 使用注解实现 AOP

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect     // 标注这是一个类的切面
public class AnnotationPointCut {

    @Before("execution(* com.lee.service.UserServiceImpl.*(..))")
    public void before() {

        System.out.println("= = = = 方法执行前 = = = =");
    }

    @After("execution(* com.lee.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("= = = = 方法执行后 = = = =");
    }

    @Around("execution(* com.lee.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕前");
//        Signature signature = joinPoint.getSignature();
//        System.out.println(signature);      // void com.lee.service.UserService.add()

        // 执行方法
        Object proceed = joinPoint.proceed();
        System.out.println("环绕后");
    }
}

package com.larinego;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotatedAspect {

    @Before("execution(* com.larinego.MainClass.aopSpringTargetMethodBefore())")
    public void before(){
        System.out.println("Before");
    }

    @After("execution(* com.larinego.MainClass.aopSpringTargetMethodBefore())")
    public void after(){
        System.out.println("After");
    }

    @Around("execution(* com.larinego.MainClass.aopSpringTargetMethodAround())")
    public void around(ProceedingJoinPoint proceedingJoinPoint)  {

        long l = System.currentTimeMillis();
        System.out.println("Around begin");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("Worktime: " + (System.currentTimeMillis() - l));
        System.out.println("Around end");

    }

    @AfterThrowing("execution(* com.larinego.MainClass.aopSpringTargetMethodWithException())")
    public void afterThrowing(){
        System.out.println("After throwing");
    }

    @Before("execution(* com.larinego.MainClass.aopSpringTargetMethod(String, String)) and args(name, surname)")
    public void beforeWork(String name, String surname){
        System.out.println("Before " + name + " " + surname + " in aspect");
    }

 /*   @Pointcut("execution(* com.larinego.MainClass.aopSpringTargetMethodBefore())")*/

}

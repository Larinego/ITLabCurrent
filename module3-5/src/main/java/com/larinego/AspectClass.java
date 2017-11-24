package com.larinego;

import org.aspectj.lang.ProceedingJoinPoint;

public class AspectClass {
    public void before(){
        System.out.println("Before");
    }

    public void after(){
        System.out.println("After");
    }

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

    public void afterThrowing(){
        System.out.println("After throwing");
    }

    public void beforeWork(String name, String surname){
        System.out.println("Before " + name + " " + surname + " in aspect");
    }

}

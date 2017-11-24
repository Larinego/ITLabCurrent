package com.larinego;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MainClassInterrceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        System.out.println(args[0]);
        if (!args[0].equals("disable")){
            return methodInvocation.proceed();
        }
        else return null;
    }
}

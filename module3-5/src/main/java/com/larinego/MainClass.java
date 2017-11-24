package com.larinego;

import org.springframework.stereotype.Service;

@Service("mainClass")
public class MainClass {

    public void proxyBasedTargetMethod(String a)
    {
        if (a.equals("exception")){
            throw new RuntimeException();
        }
        System.out.println("proxyBasedTargetMethod method completeed.");
    }

    public void aopSpringTargetMethodBefore(){
        System.out.println("aopSpringTargetMethodBefore method completeed.");
    }

    public void aopSpringTargetMethod(String name, String surname){
        System.out.println("aopSpringTargetMethod method completeed. " + name + " " + surname + " in method");
    }

    public void aopSpringTargetMethodAround(){
        System.out.println("aopSpringTargetMethodAround method completeed.");
    }

    public void aopSpringTargetMethodWithException(){
        throw new RuntimeException();
    }


}

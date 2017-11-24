package com.larinego;


public class MySender implements ISender {

    @Override
    public void send(Message message) {
        System.out.println(message.getMessage());
    }
}

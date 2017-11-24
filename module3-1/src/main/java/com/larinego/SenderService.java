package com.larinego;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SenderService {

    private ISender sender;

    public void send(Message message){
        sender.send(message);
    }
}

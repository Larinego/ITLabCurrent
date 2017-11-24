package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Address {

    @Value("Minsk")
    private String city;

    @Value("Lenina")
    private String street;

}

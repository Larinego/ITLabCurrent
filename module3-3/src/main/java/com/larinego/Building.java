package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
public class Building {

    @Autowired
    private Address address;

    @Autowired(required = false)
    private Host host;

    @Autowired
    @Qualifier("bathroom")
    private Room bathroom;

    @Autowired
    @KitchenAnnotation
    private Room kitchen;


}

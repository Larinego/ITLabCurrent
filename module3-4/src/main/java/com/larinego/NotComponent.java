package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service("notComponent")
@MyAddressAnnotation
public class NotComponent {

    @Value("not wired")
    private String name;
}

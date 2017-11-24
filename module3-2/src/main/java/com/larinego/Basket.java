package com.larinego;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Basket {
    Map<Product,Integer> basket;
}

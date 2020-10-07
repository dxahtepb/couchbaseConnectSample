package com.example.taco.data;

import com.example.taco.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}

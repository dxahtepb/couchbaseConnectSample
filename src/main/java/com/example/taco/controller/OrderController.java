package com.example.taco.controller;

import com.example.taco.data.OrderRepository;
import com.example.taco.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrderForm(@Validated Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Processing order: " + order);
        Order saved = orderRepository.save(order);
        log.info("Saved order: " + saved);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}

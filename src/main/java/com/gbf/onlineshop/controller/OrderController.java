package com.gbf.onlineshop.controller;

import com.gbf.onlineshop.model.Order;
import com.gbf.onlineshop.model.OrderGoods;
import com.gbf.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{login}")
    public List<Order> getAllById(@PathVariable String login) {
        return orderService.getAllByLogin(login);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }

    @PutMapping("/ordergoods")
    public OrderGoods addGood(@RequestBody @Valid OrderGoods orderGoods) {
        return orderService.addGood(orderGoods);
    }

}

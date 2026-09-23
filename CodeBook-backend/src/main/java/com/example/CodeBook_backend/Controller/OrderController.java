        package com.example.CodeBook_backend.Controller;

import com.example.CodeBook_backend.Service.OrderService;
import com.example.CodeBook_backend.model.Orders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/660/orders")
@CrossOrigin(origins = "https://codebook2.netlify.app")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUserId(
            @PathVariable Long userId) {

        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }
}


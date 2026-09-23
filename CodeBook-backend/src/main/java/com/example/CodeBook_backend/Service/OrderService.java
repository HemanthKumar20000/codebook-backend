
        package com.example.CodeBook_backend.Service;

import com.example.CodeBook_backend.repo.OrderRepository;
import com.example.CodeBook_backend.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    public List<Orders> getOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}


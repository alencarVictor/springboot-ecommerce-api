package com.victor.ecommerceapi.controller;

import com.victor.ecommerceapi.model.Order;
import com.victor.ecommerceapi.model.OrderStatus;
import com.victor.ecommerceapi.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {



    //Atributos
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //metodos da classe
    @GetMapping
    public List<Order> getAll(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getByID( @PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PatchMapping("/{id}")
    public Order updateStatus(@PathVariable Long id,
                              @RequestBody Map<String, String> body) {

        OrderStatus status = OrderStatus.valueOf(body.get("status"));
        return orderService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id){
        orderService.deleteById(id);
    }

}

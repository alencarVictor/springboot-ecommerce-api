package com.victor.ecommerceapi.service;

import com.victor.ecommerceapi.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    //Atributo
    private List<Order> orders = new ArrayList<>();
    private Long currentId = 1L;

    //Construtor vazio
    public OrderService() {
    }

    //Metodos da classe
    public Order createOrder(Order order){
        order.setId(currentId++);
        order.setTotal(order.calculateTotal());
        orders.add(order);
        return order;
    }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders);

    }
    public Order getById(Long id){
        for (Order order : orders){
            if (id.equals(order.getId())){
                return order;
            }
        }
        return null;
    }
}
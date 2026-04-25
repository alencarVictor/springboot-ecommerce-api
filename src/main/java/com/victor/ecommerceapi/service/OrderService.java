package com.victor.ecommerceapi.service;

import com.victor.ecommerceapi.model.Order;
import com.victor.ecommerceapi.model.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    //POST cria order
    public Order createOrder(Order order){
        order.setId(currentId++);

        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(order.calculateTotal());
        orders.add(order);
        return order;
    }
    //GET  lista tudo
    public List<Order> getAllOrders(){
        return new ArrayList<>(orders);

    }

    //GET lista por ID
    public Order getById(Long id){
        for (Order order : orders){
            if (id.equals(order.getId())){
                return order;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Pedido não encotrado"
        );
    }

    //PATCH atualiza status pedido
    public Order updateStatus (Long id , OrderStatus status){
        Order order = getById(id);
        order.setStatus(status);
        return order;
    }

    //DELETE remove um pedido da lista
    public void deleteById (Long id){
        Order order = getById(id);
        orders.remove(order);
    }
}
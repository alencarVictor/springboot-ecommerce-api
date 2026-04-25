package com.victor.ecommerceapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    //Atributos
    private Long id;
    private List<OrderItem> items;
    private Double total;
    private OrderStatus status;
    private LocalDateTime createdAt;

    //Construtor Vazio
    public Order() {
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    //Construtor com parametros
    public Order(Long id, List<OrderItem> items, OrderStatus status, LocalDateTime createdAt){
        this.id = id;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
    }



    //Getters e Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public List<OrderItem> getItems() {return items;}
    public void setItems(List<OrderItem> items) {this.items = items;}

    public Double getTotal() {return total;}
    public void setTotal(Double total) {this.total = total;}

    public OrderStatus getStatus() {return status;}
    public void setStatus(OrderStatus status) {this.status = status;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}


    //Metodo da classe
    public Double calculateTotal(){
        double total =0;
        for (OrderItem item : items){
            total += item.getTotal();
        }
        this.total = total;
        return total;
    }
}

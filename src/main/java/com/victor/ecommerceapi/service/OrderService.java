package com.victor.ecommerceapi.service;

import com.victor.ecommerceapi.model.Order;
import com.victor.ecommerceapi.model.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    //Atributo
    private final DynamoDbClient dynamoDbClient;

    private List<Order> orders = new ArrayList<>();
    private Long currentId = 1L;

    //Construtor
    public OrderService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }


    //Metodos da classe

    //POST cria order
    public Order createOrder(Order order) {

        order.setId(currentId++);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(order.calculateTotal());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("orders")
                .item(
                        Map.of(
                                "id", AttributeValue.builder().s(order.getId().toString()).build(),
                                "status", AttributeValue.builder().s(order.getStatus().name()).build(),
                                "createdAt", AttributeValue.builder().s(order.getCreatedAt().toString()).build(),
                                "total", AttributeValue.builder().n(String.valueOf(order.getTotal())).build()
                        )
                )
                .build();

        dynamoDbClient.putItem(request);

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
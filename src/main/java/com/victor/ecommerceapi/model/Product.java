package com.victor.ecommerceapi.model;

public class Product {

    //Atributos
    private Long id;
    private String name;
    private Double price;
    private Integer stock;

    //Construtor vazio
    public Product() {
    }

    //Construtor com parametros
    public Product(long id, String name, Double prince, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    //Getters e Setters
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Double getPrice() {return price;}
    public void setPrince(Double prince) {this.price = prince;}

    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}
}

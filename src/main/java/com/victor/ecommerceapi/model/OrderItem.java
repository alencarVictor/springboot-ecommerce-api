package com.victor.ecommerceapi.model;

public class OrderItem {

    //Atributos
    private Long id;
    private Product product;
    private Integer quantivy;
    private Double price;

    //Construtor Vazio
    public OrderItem() {
    }

    //Construtor com parametros
    public OrderItem(Product product, Integer quantivy) {
        this.product = product;
        this.quantivy = quantivy;
    }

    //Getters e Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}

    public Integer getQuantivy() {return quantivy;}
    public void setQuantivy(Integer quantivy) {this.quantivy = quantivy;}

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}

    //Metodo da classe
    public Double getTotal(){
        return product.getPrice() * quantivy;
    }
}
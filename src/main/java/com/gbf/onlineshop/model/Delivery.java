package com.gbf.onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Double price;

    public Delivery() {
    }

    public Delivery(String type, Double price) {
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

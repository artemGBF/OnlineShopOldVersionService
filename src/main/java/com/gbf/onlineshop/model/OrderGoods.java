package com.gbf.onlineshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderGoods {
    @Id
    @GeneratedValue
    private Long id;
    private Integer count;

    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Good good;

    public OrderGoods() {
    }

    public OrderGoods(Integer count, Order order, Good good) {
        this.count = count;
        this.order = order;
        this.good = good;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}

package com.gbf.onlineshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stockpile {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private int count;

    @ManyToMany
    private List<Good> goods;

    public Stockpile() {
    }

    public Stockpile(String number, int count) {
        this.number = number;
        this.count = count;
    }

    public Stockpile(String number, int count, List<Good> goods) {
        this.number = number;
        this.count = count;
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Stockpile{" +
                "id=" + id +
                ", count=" + count +
                ", goods=" + goods +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}

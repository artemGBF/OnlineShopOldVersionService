package com.gbf.onlineshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Status {
    public final static Long PAYED_ID = 3L;
    public final static Long PREPARE_ID=4L;
    public final static Long DELIVERED_ID=5L;
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    public Status(String name, List<Order> orders) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

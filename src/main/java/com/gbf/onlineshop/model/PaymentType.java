package com.gbf.onlineshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PaymentType {
    @Id
    @GeneratedValue
    private Long id;
    private boolean type;

    public PaymentType(boolean type) {
        this.type = type;
    }

    public PaymentType() {
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }


}

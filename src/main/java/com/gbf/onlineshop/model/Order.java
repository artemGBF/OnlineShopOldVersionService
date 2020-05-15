package com.gbf.onlineshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String number;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_delivery")
    @NotNull(message = "поле доставки не может быть пустым")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "id_client")
    @NotNull(message = "Поле клиента не должно быть пустым")
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_payment_type")
    @NotNull(message = "поле Оплаты не может быть пустым")
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderGoods> orderGoods;

    public Order() {
    }

    public Order(String number, LocalDateTime date, Delivery delivery, @NotNull Client client, Status status, PaymentType paymentType) {
        this.number=number;
        this.date=date;
        this.delivery = delivery;
        this.client = client;
        this.status = status;
        this.paymentType = paymentType;
    }

    public static String[] getTags(){
        return new String[]{"number", "date", "delivery", "client", "status", "PaymentType"};
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", delivery=" + delivery +
                ", client=" + client +
                ", status=" + status +
                ", paymentType=" + paymentType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

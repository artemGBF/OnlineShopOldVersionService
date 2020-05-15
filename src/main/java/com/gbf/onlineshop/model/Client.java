package com.gbf.onlineshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gbf.onlineshop.types.UserRole;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> orders;

    public Client() {
    }

    public Client(String login, String password, String mail) {
        super(login, password, mail);
    }

    public Client(String login, String password, String mail, List<Order> orders) {
        super(login, password, mail);
        this.orders = orders;
    }

    public String[] getTags(){
        return new String[]{"login", "mail", "createdDate"};
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public UserRole getRole() {
        return UserRole.CLIENT;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}

package com.gbf.onlineshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Good {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Integer price;

    @ManyToOne
    private Categoria categoria;

    @ManyToMany(mappedBy = "goods")
    private List<Stockpile> stockpile;

    public Good() {
    }

    public Good(String name, Integer price, Categoria categoria) {
        this.name = name;
        this.price = price;
        this.categoria = categoria;
    }

    public Good(String name, Integer price, Categoria categoria, List<Stockpile> stockpile) {
        this.name = name;
        this.price = price;
        this.categoria = categoria;
        this.stockpile = stockpile;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoria=" + categoria +
                ", stockpile=" + stockpile +
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Stockpile> getStockpile() {
        return stockpile;
    }

    public void setStockpile(List<Stockpile> stockpile) {
        this.stockpile = stockpile;
    }
}

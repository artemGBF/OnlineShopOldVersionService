package com.gbf.onlineshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long idParentCategoria;

    public Categoria() {
    }

    public Categoria(String name, Long idParentCategoria) {
        this.name = name;
        this.idParentCategoria = idParentCategoria;
    }

    public Categoria(String name) {
        this.name = name;
    }

    public Categoria(String name, Long idParentCategoria, List<Good> good) {
        this.name = name;
        this.idParentCategoria = idParentCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idParentCategoria=" + idParentCategoria +
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

    public Long getIdParentCategoria() {
        return idParentCategoria;
    }

    public void setIdParentCategoria(Long idParentCategoria) {
        this.idParentCategoria = idParentCategoria;
    }
}
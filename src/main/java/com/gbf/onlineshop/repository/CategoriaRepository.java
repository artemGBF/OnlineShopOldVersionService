package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    Categoria findByName(String name);
}

package com.gbf.onlineshop.controller;

import com.gbf.onlineshop.model.Categoria;
import com.gbf.onlineshop.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getAll(){
       // SecurityContext securityContext = SecurityContextHolder.getContext();
        return categoriaService.getAll();
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable Long id){
        throw new MethodNotAllowedException(HttpMethod.GET, Collections.singleton(HttpMethod.GET));
    }
}

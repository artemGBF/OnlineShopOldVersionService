package com.gbf.onlineshop.service;

import com.gbf.onlineshop.model.Categoria;
import com.gbf.onlineshop.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PreAuthorize("@accessService.clientOrAdmin()")
    public List<Categoria> getAll(){
        return (List<Categoria>) categoriaRepository.findAll();
    }
}

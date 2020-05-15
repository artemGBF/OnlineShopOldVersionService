package com.gbf.onlineshop.service;

import com.gbf.onlineshop.exceptions.ResourceNotFoundException;
import com.gbf.onlineshop.model.Categoria;
import com.gbf.onlineshop.model.Good;
import com.gbf.onlineshop.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    public List<Good> findAll(){
        return (List<Good>) goodRepository.findAll();
    }


    public Good findById(Long id){
        return goodRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Товар", id));
    }

    @Transactional
    public Good update(Long id, Good good){
        Good byId = findById(id);
        byId.setCategoria(good.getCategoria());
        return byId;
    }

    public Good save(Good good){
        return goodRepository.save(good);
    }

    public void delete(Long id){
         this.goodRepository.deleteById(id);
    }
}

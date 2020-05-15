package com.gbf.onlineshop.controller;

import com.gbf.onlineshop.model.Good;
import com.gbf.onlineshop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping
    public ResponseEntity<List<Good>> getAll() {
        return ResponseEntity.ok(goodService.findAll());
    }

    @GetMapping("/{id}")
    public Good getById(@PathVariable Long id) {
        return goodService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Good addGood(@Valid @RequestBody Good good) {
        return goodService.save(good);
    }

    @PutMapping("/{id}")
    public Good update(@PathVariable Long id, @Valid @RequestBody Good good) {
        return goodService.update(id, good);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@Param(value = "id") Long id) {
        goodService.delete(id);
    }

}

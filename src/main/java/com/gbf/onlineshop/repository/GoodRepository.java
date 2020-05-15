package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.Good;
import org.springframework.data.repository.CrudRepository;

public interface GoodRepository extends CrudRepository<Good, Long> {
}

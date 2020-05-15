package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    Delivery findByType(String type);
}

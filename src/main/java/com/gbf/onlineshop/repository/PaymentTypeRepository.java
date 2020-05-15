package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
    PaymentType findByType(boolean type);
}

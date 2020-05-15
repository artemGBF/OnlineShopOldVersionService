package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT u FROM Order u where u.date between :dateStart and :dateEnd")
    List<Order> findAllByDate(@Param("dateStart") LocalDateTime dateStart, @Param("dateEnd") LocalDateTime dateEnd);

    List<Order> findAllByDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);

    List<Order> findAllByClient_Login(String login);
}

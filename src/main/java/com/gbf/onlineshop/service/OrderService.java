package com.gbf.onlineshop.service;

import com.gbf.onlineshop.aspects.annotations.LogTime;
import com.gbf.onlineshop.configuration.security.UserDetailsImpl;
import com.gbf.onlineshop.exceptions.ResourceNotFoundException;
import com.gbf.onlineshop.model.*;
import com.gbf.onlineshop.repository.*;
import com.gbf.onlineshop.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private OrderGoodsRepository orderGoodsRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public Order saveOrder(@Valid @NotNull Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllByLogin(String login) {
        return orderRepository.findAllByClient_Login(login);
    }

    @PreAuthorize("@accessService.clientOnly()")
    public void createOrder(Order order) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client user = (Client) userDetails.getUser();
        order.setClient(user);
        order.setNumber("random");
        Status status = statusRepository.findById(Status.PREPARE_ID).orElseThrow(()->new ResourceNotFoundException("Статус", Status.PREPARE_ID));
        order.setStatus(status);
        order.setDate(LocalDateTime.now());
       orderRepository.save(order);
    }

    @PreAuthorize("@accessService.clientOnly()")
    public OrderGoods addGood(OrderGoods orderGoods) {
        return orderGoodsRepository.save(orderGoods);
    }

    @Transactional
    public void testTransaction() {
        orderRepository.deleteById(5L);
        throw new NullPointerException();
    }

    @Transactional
    @LogTime
    public void testPersistance() {
        Order order = orderRepository.findById(5L).orElseThrow(() -> new RuntimeException("order is not found"));
        order.setNumber("lalala");
        entityManager.detach(order);
    }

}

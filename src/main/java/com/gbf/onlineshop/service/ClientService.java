package com.gbf.onlineshop.service;

import com.gbf.onlineshop.aspects.annotations.SecurePassword;
import com.gbf.onlineshop.model.Client;
import com.gbf.onlineshop.model.Order;
import com.gbf.onlineshop.repository.ClientRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void testFetch(){
        Client client = clientRepository.findById(4L).orElseThrow(() -> new RuntimeException("client with id=4 not found"));
        Hibernate.initialize(client.getOrders());
        client.getOrders().forEach(System.out::println);
    }

    @Transactional
    public Client update(Long id, String password){
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        client.setPassword(password);
        return client;
    }

    @Transactional
    public Client add(Client c){
        return clientRepository.save(c);
    }

    public Client getById(Long id){
        Client client = clientRepository.findById(id).get();
        return client;
    }

    @Transactional
    public void testCascade(){
        //clientRepository.deleteById(10L);
        Client client = new Client("login", "password", "mail4", new ArrayList<>());
        Order order = new Order("jjjjj", LocalDateTime.now(), null, client, null, null);
        client.getOrders().add(order);
        clientRepository.save(client);
    }
}

package com.gbf.onlineshop.database;

import com.gbf.onlineshop.model.*;
import com.gbf.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@Profile("postgreLoad")
public class DBInitializer {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private StockpileRepository stockpileRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private OrderGoodsRepository orderGoodsRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void fillDB(){
        PaymentType paymentType1 = paymentTypeRepository.save(new PaymentType(true));
        PaymentType paymentType2 = paymentTypeRepository.save(new PaymentType(false));

        Status status1 = statusRepository.save(new Status("Оплачено"));
        Status status2 = statusRepository.save(new Status("Сборка"));
        Status status3 = statusRepository.save(new Status("Доставка"));

        Delivery delivery1 = deliveryRepository.save(new Delivery("Курьером", 149D));
        Delivery delivery2 = deliveryRepository.save(new Delivery("Курьером-экспресс", 259D));
        Delivery delivery3 = deliveryRepository.save(new Delivery("почта РФ", 39D));

        Client client1 = clientRepository.save(new Client("Mike", passwordEncoder.encode("Tyson"), "mail1"));
        Client client2 = clientRepository.save(new Client("Roy", passwordEncoder.encode("Johns"), "mail2"));
        Client client3 = clientRepository.save(new Client("Robert", passwordEncoder.encode("Nealan"), "mail3"));
        Admin admin = adminRepository.save(new Admin("Admin", passwordEncoder.encode("root"), "admin@gmail.com"));

        Categoria categoria1 = new Categoria("Заморозка");
        categoriaRepository.save(categoria1);
        Categoria frosten = categoriaRepository.findByName("Заморозка");
        categoriaRepository.save(frosten);
        Categoria categoria11 = new Categoria("Замороженная рыба",frosten.getId());
        Categoria categoria12 = new Categoria("Замороженное мясо", frosten.getId());
        Categoria categoria13 = new Categoria("Полуфабрикаты", frosten.getId());
        Categoria categoria2 = new Categoria("Кулинария");
        categoriaRepository.save(categoria11);
        categoriaRepository.save(categoria12);
        categoriaRepository.save(categoria13);
        categoriaRepository.save(categoria2);
        Categoria kitchen = categoriaRepository.findByName("Кулинария");
        Categoria categoria21 = new Categoria("Мясная кухня", kitchen.getId());
        categoriaRepository.save(categoria21);
        Categoria categoria211 = new Categoria("Жареное мясо", categoriaRepository.findByName("Мясная кухня").getId());
        Categoria categoria212 = new Categoria("Тушеное мясо", categoriaRepository.findByName("Мясная кухня").getId());
        Categoria categoria22 = new Categoria("Рыбная кухня", kitchen.getId());
        Categoria categoria23 = new Categoria("Птичья кухня", kitchen.getId());
        categoriaRepository.save(categoria211);
        categoriaRepository.save(categoria212);
        categoriaRepository.save(categoria22);
        categoriaRepository.save(categoria23);

        Good good1 = new Good("ростбиф",100, categoriaRepository.findByName("Жареное мясо"));
        Good good2 = new Good("стейк риплойн",133, categoriaRepository.findByName("Жареное мясо"));
        Good good3 = new Good("гуляш",90, categoriaRepository.findByName("Тушеное мясо"));
        Good good4 = new Good("Замороженный окунь",101, categoriaRepository.findByName("Замороженная рыба"));
        Good good5 = new Good("Замороженный палтус",92, categoriaRepository.findByName("Замороженная рыба"));
        Good good6 = new Good("Замороженная скумбрия",70, categoriaRepository.findByName("Замороженная рыба"));
        Good good7 = new Good("Пельмени",50, categoriaRepository.findByName("Полуфабрикаты"));

        goodRepository.save(good1);
        goodRepository.save(good2);
        goodRepository.save(good3);
        goodRepository.save(good4);
        goodRepository.save(good5);
        goodRepository.save(good6);
        goodRepository.save(good7);

        Order order1 = new Order("sdjfkd1", LocalDateTime.now(), delivery1, client1, status1, paymentType1);
        Order order2 = new Order("jghfjj2", LocalDateTime.now().minusDays(30), delivery2, client1, status1, paymentType1);
        Order order3 = new Order("4hfjf4f", LocalDateTime.now().minusHours(31),delivery1, client2, status3, paymentType1);
        Order order4 = new Order("5jfj4ks", LocalDateTime.now().minusMinutes(567), delivery1, client1, status1, paymentType1);
        Order order5 = new Order("5jgjf3k", LocalDateTime.now().minusHours(578),delivery3, client3, status2, paymentType2);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        orderRepository.save(order5);

    }
}

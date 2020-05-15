package com.gbf.onlineshop;

import com.gbf.onlineshop.repository.AdminRepository;
import com.gbf.onlineshop.repository.OrderRepository;
import com.gbf.onlineshop.repository.UserRepository;
import com.gbf.onlineshop.service.ClientService;
import com.gbf.onlineshop.service.OrderService;
import com.gbf.onlineshop.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.security.Key;
import java.util.Date;

public class FakeProgramRunner implements CommandLineRunner {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    public FakeProgramRunner(String str) {
        System.out.println("str = " + str);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Client peter = clientService.add(new Client("Bugai2", "1234"));
        Thread.sleep(3000);
        peter =clientService.update(peter.getId(),"qwerty");
        System.out.println(peter.getCreatedDate());
        System.out.println(peter.getModifiedDate());*/

        //clientService.testCascade();


        //orderService.saveOrder(null);
        /*try {
            //orderService.testTransaction();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        orderService.testPersistance();
        clientService.testFetch();*/

        /*userRepository.findAll().forEach(System.out::println);
        System.out.println("-----------------------------");
        adminRepository.findAll().forEach(System.out::println);*/

       /* List<Order> allByDate = orderRepository.findAllByDate(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        List<Order> allByDate2 = orderRepository.findAllByDateBetween(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        allByDate2.forEach(System.out::println);*/

        /*Iterable<Admin> all = adminRepository.findAll();
        all.forEach(System.out::println);*/

        /*Client byId = clientService.getById(9L);
        System.out.println(byId);*/

        /*CheckFukingReflection checkFukingReflection = userService.get();
        System.out.println(checkFukingReflection);*/

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jwsString = Jwts.builder()
                .setSubject("Joe")
                .claim("test", "testValue")
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis()+10000))
                .compact();

        System.out.println(jwsString);

        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(key) // <----
                .build()
                .parseClaimsJws(jwsString);
        Claims body = jwsClaims.getBody();
        System.out.println(body.get("test"));
        System.out.println(body);
        System.out.println(jwsClaims);
    }
}

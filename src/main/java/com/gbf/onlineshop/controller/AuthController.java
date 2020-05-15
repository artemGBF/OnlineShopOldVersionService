package com.gbf.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class AuthController {

    /*@GetMapping("/login")
    public String open(){
        return "login.html";
    }*/

    @GetMapping("/refresh")
    public void doRefresh(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> token){

    }
}

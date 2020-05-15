package com.gbf.onlineshop.configuration.security.deprecated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbf.onlineshop.dto.AuthorisationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Deprecated
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/api")) {
            System.out.println("JWTAuthenticationFilter.doFilterInternal");
            Cookie cookie = WebUtils.getCookie(request, "COOKIE-BEARER");
            if (cookie == null) {
                throw new RuntimeException("No cookie");
            }
            String value = cookie.getValue();
            //jwtService.authenticate(value);


            //=====================================================
            String validateUrl = "http://localhost:8081/validate";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> authToken = Map.of("authToken", value);
            HttpEntity<String> restRequest = new HttpEntity<>(new ObjectMapper().writeValueAsString(authToken), headers);
            AuthorisationData result = restTemplate.postForObject(validateUrl, restRequest, AuthorisationData.class);
            System.out.println("result = " + result);
            List<SimpleGrantedAuthority> grantedAuthorities = result.getAuthorities().stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            Authentication auth = new UsernamePasswordAuthenticationToken(result.getLogin(), null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}

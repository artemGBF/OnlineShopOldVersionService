package com.gbf.onlineshop.configuration.security.deprecated;

import com.gbf.onlineshop.configuration.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Deprecated
public class JWTService {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String giveAuthToken(Authentication authResult) {
        UserDetailsImpl principal = (UserDetailsImpl) authResult.getPrincipal();
        String jwsString = Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("authorities", authResult.getAuthorities())
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .compact();
        return jwsString;
    }

    public String giveRefreshToken(Authentication authResult) {
        UserDetailsImpl principal = (UserDetailsImpl) authResult.getPrincipal();
        String jwsString = Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("authorities", authResult.getAuthorities())
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4))
                .compact();
        return jwsString;
    }

    public void authenticate(String jwsString) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwsString);
        String subject = claimsJws.getBody().getSubject();
        ArrayList<LinkedHashMap<String, String>> authorities = (ArrayList<LinkedHashMap<String, String>>) claimsJws.getBody().get("authorities");
        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().flatMap(t -> t.values().stream())
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        Authentication auth = new UsernamePasswordAuthenticationToken(subject, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    //TODO 1. выдать новую пару откенов(один в куки один в тело) 2. достать пользователя из БД 3. механизм валидации
    public void refresh(HttpServletRequest request, HttpServletResponse response, Map<String, String> token) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(t -> t.getName().equals("COOKIE-BEARER")).findAny().orElseThrow(()->new RuntimeException("no"));
        String value = cookie.getValue();

    }
}

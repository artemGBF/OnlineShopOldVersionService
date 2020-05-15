package com.gbf.onlineshop.configuration.security.deprecated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbf.onlineshop.exceptions.AuthParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Deprecated
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private JWTService jwtService;

    public JWTLoginFilter(AuthenticationManager manager) {
        super();
        setAuthenticationManager(manager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            JsonAuthData data = new ObjectMapper().readValue(request.getInputStream(), JsonAuthData.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword())
            );
        } catch (IOException e) {
            throw new AuthParseException("wrong params in auth request");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("JWTLoginFilter.successfulAuthentication");
        String token = jwtService.giveAuthToken(authResult);
        Cookie cookie = new Cookie("COOKIE-BEARER", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        //TODO время жихни должно быть 3-4 часа
        response.getWriter().write(new ObjectMapper().writeValueAsString(Map.of("refreshToken",
                jwtService.giveRefreshToken(authResult))));
        //chain.doFilter(request, response);
    }

    private static class JsonAuthData{
        private String login;
        private String password;

        public JsonAuthData() {
        }

        public JsonAuthData(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}

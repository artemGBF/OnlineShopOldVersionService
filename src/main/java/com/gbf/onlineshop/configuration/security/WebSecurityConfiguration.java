package com.gbf.onlineshop.configuration.security;



import com.gbf.auth.filter.integration.JWTAuthenticationFilter;
import com.gbf.onlineshop.types.UserRole;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            //.addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/orders/**").hasAnyAuthority(UserRole.CLIENT.name())
                .antMatchers(HttpMethod.PUT,"/api/orders/**").hasAnyAuthority(UserRole.CLIENT.name())
                .antMatchers(HttpMethod.GET,"/api/categories").hasAnyAuthority(Arrays.stream(UserRole.values()).map(Enum::name).toArray(String[]::new))
                .antMatchers("/api/**").hasAuthority(UserRole.ADMIN.name())
                //.antMatchers("/login*").permitAll()
                //.antMatchers("/api/**").not()
                .anyRequest().authenticated()
            .and()
                .httpBasic().disable();//TODO прочитать про этот метод.
            /*.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/api/categories", true)
                .failureUrl("/login?error=true")
            .and()*/
            /*.logout()
                .deleteCookies("COOKIE-BEARER")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));*/
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public JWTLoginFilter jwtLoginFilter() throws Exception {
        return new JWTLoginFilter(authenticationManager());
    }*/

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean<JWTAuthenticationFilter> jwtAuthenticationFilterBean(){
        FilterRegistrationBean<JWTAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/api/**");
        return filterRegistrationBean;
    }

}

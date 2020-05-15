package com.gbf.onlineshop.configuration.security;

import com.gbf.onlineshop.types.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

    public boolean clientOrAdmin(){
        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(UserRole.CLIENT.name());
        SimpleGrantedAuthority simpleGrantedAuthority2 = new SimpleGrantedAuthority(UserRole.ADMIN.name());
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.contains(simpleGrantedAuthority2)||authorities.contains(simpleGrantedAuthority1);
    }

    public boolean clientOnly(){
        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(UserRole.CLIENT.name());
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.contains(simpleGrantedAuthority1);
    }
}

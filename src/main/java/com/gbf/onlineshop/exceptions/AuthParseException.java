package com.gbf.onlineshop.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthParseException extends AuthenticationException {

    public AuthParseException(String msg) {
        super(msg);
    }
}

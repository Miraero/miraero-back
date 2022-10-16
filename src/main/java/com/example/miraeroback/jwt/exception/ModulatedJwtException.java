package com.example.miraeroback.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class ModulatedJwtException extends AuthenticationException {
    public ModulatedJwtException(String msg){
        super(msg);
    }
}

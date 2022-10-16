package com.example.miraeroback.login;

import com.example.miraeroback.login.dto.DtoOfLoginSuccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    DtoOfLoginSuccess login(String authorizationCode) throws JsonProcessingException;
}

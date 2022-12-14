package com.example.miraeroback.jwt.handler;

import com.example.miraeroback.jwt.dto.DtoOfJwtException;
import com.example.miraeroback.jwt.exception.ExpiredJwtException;
import com.example.miraeroback.jwt.exception.ModulatedJwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();

        if(exception instanceof ExpiredJwtException){
            writer.write(objectMapper.writeValueAsString(DtoOfJwtException.builder()
                    .msg("만료된 토큰입니다.").build()));
        }

        if(exception instanceof ModulatedJwtException){

            writer.write(objectMapper.writeValueAsString(DtoOfJwtException.builder()
                    .msg("잘못된 토큰입니다.").build()));

        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_ENCODING, "UTF-8");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);




    }
}

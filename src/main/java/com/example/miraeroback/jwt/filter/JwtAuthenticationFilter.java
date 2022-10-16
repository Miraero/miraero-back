package com.example.miraeroback.jwt.filter;

import com.example.miraeroback.jwt.dto.DtoOfJwtPostAuthenticationToken;
import com.example.miraeroback.jwt.dto.DtoOfUserDataFromJwt;
import com.example.miraeroback.jwt.model.AuthUser;
import com.example.miraeroback.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 사용자의 요청에 존재하는 Authorization Header Value
        String authorizationHeader = ((HttpServletRequest)request).getHeader(AUTHORIZATION_HEADER);


        if(authorizationHeader == null){
            chain.doFilter(request, response);
            return;
        }

        // Remove Prefix (Bearer)
        String token = getToken(authorizationHeader);

        DtoOfUserDataFromJwt userPayloads = jwtProvider.getUserData(token);

        AuthUser context = AuthUser.builder()
                .id(String.valueOf(userPayloads.getId()))
                .userPayloads(userPayloads).build();

        DtoOfJwtPostAuthenticationToken authentication = new DtoOfJwtPostAuthenticationToken(context);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
    private String getToken(String authorizationHeader){
        return authorizationHeader.substring(BEARER_PREFIX.length());
    }
}

package com.example.miraeroback.auth.util;

import com.example.miraeroback.auth.dto.DtoOfOauthTokenResponse;
import com.example.miraeroback.auth.dto.DtoOfUserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Component
public class Oauth2Util {

    @Autowired
    private final Oauth2RequestUtil oauth2RequestUtil;

    /**
     * Oauth2 Authorization Server로 부터 Access Token을 받기 위한 메서드
     * @param code : Oauth2 Authorization Server 로 부터 받은 Authorization Code
     * @return DtoOfOauthTokenResponse : Oauth2 Authorization Server 로 부터 받은 AccessToken
     * @throws JsonProcessingException
     */
    public DtoOfOauthTokenResponse getToken(String code) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<?> kakaoTokenRequest =
                new HttpEntity<>(generateParam(code), headers);

        DtoOfOauthTokenResponse dtoOfOauthTokenResponse = oauth2RequestUtil.requestAuth(kakaoTokenRequest);

        return dtoOfOauthTokenResponse;
    }

    /**
     * Oauth2 Authorization Server 로 요청을 보낼 파라미터를 생성하는 메서드
     * @param code : Oauth2 Authorization Server 로 부터 받은 Authorization Code
     * @return MultiValudeMap<String, String> : 요청을 보낼 파라미터를 가지고있는 객체
     */
    private MultiValueMap<String, String> generateParam(String code){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", "1e9f53ef5d1b41846fe3d2e9e47a0e51");
        params.add("redirect_uri", "http://localhost:8080/redirect/oauth");
        params.add("code", code);
        params.add("client_secret", "rcgeTZx1l9QBCMGPNY6TwUnjbU8dS5FV");

        return params;
    }

    /**
     * Oauth2 Resource Server로 AccessToken을 통해 유저 정보를 요청하는 메서드
     * @param accessToken : Oauth2 Authorization Server 로 부터 받은 Access Token
     * @return DtoOfUserProfile : Oauth2 Resource Server 로 부터 받은 유저 정보
     * @throws JsonProcessingException
     */
    public DtoOfUserProfile getUserProfile(String accessToken) throws JsonProcessingException {

        DtoOfUserProfile dtoOfUserProfile = oauth2RequestUtil.requestProfile(accessToken);

        return dtoOfUserProfile;
    }

}

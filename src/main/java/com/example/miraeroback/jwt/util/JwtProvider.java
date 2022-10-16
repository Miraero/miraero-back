package com.example.miraeroback.jwt.util;

import com.example.miraeroback.jwt.dto.DtoOfUserDataFromJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtProvider {

    String accessTokenKey = "sample";

    String refreshTokenKey = "sample";

    public void authenticateAccessToken(String accessToken){


        Jwts.parser()
                .setSigningKey(accessTokenKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(accessToken)
                .getBody();
    }

    public void authenticateRefreshToken(String refreshToken){
        Jwts.parser().setSigningKey(refreshTokenKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(refreshToken).getBody();
    }

    public DtoOfUserDataFromJwt getUserData(String accessToken){

        Claims claims = getClaims(accessToken, this.accessTokenKey);


        return DtoOfUserDataFromJwt.builder()
                .id(Long.parseLong(claims.getSubject()))
                .nickname(claims.get("nickname").toString())
                .build();


    }

    public boolean checkRenewRefreshToken(String refreshToken, Long time){


        Instant now = Instant.now();
        Instant expiredTime = getClaims(refreshToken, this.refreshTokenKey).getExpiration().toInstant();

        long diffTIme = now.until(expiredTime, ChronoUnit.DAYS);

        return diffTIme < time;
    }
    private Claims getClaims(String token, String tokenKey){
        return Jwts.parser()
                .setSigningKey(tokenKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}

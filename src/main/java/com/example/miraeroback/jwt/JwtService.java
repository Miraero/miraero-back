package com.example.miraeroback.jwt;

import com.example.miraeroback.jwt.dto.DtoOfJwt;
import com.example.miraeroback.jwt.dto.DtoOfUserDataFromJwt;
import com.example.miraeroback.user.User;

public interface JwtService {

    DtoOfJwt createTokenDto(User user);


    DtoOfUserDataFromJwt getUserData(String accessToken);

    DtoOfJwt refresh(String refreshToken);

    boolean isExistsRefreshToken(String refreshToken);

    void saveRefreshToken(String accessToken, String refreshToken, User user);

}

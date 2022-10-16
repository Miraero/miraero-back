package com.example.miraeroback.login;

import com.example.miraeroback.auth.dto.DtoOfOauthTokenResponse;
import com.example.miraeroback.auth.dto.DtoOfUserProfile;
import com.example.miraeroback.auth.util.Oauth2Util;
import com.example.miraeroback.login.dto.DtoOfLoginSuccess;
import com.example.miraeroback.user.User;
import com.example.miraeroback.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

//    @Autowired
//    private final JwtService jwtService;

    @Autowired
    private final Oauth2Util oauth2Util;


    @Override
    public DtoOfLoginSuccess login(String authorizationCode) throws JsonProcessingException {
        DtoOfOauthTokenResponse oauthTokenResponse = oauth2Util.getToken(authorizationCode);
        DtoOfUserProfile userProfile = oauth2Util.getUserProfile(oauthTokenResponse.getAccessToken());
        User userEntity = userService.login(userProfile);

        return DtoOfLoginSuccess.builder()
                .id(userEntity.getId())
                .nickName(userEntity.getNickname())
                .build();
    }
}

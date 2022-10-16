package com.example.miraeroback.login;

import com.example.miraeroback.auth.dto.DtoOfOauthTokenResponse;
import com.example.miraeroback.auth.dto.DtoOfUserProfile;
import com.example.miraeroback.auth.util.Oauth2Util;
import com.example.miraeroback.jwt.JwtService;
import com.example.miraeroback.jwt.dto.DtoOfJwt;
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

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final Oauth2Util oauth2Util;


    @Override
    public DtoOfLoginSuccess login(String authorizationCode) throws JsonProcessingException {

        // 시연용 아이디 코드
        if(authorizationCode.equals("1")){
            User userEntity = userService.login(mockUser());
            DtoOfJwt dtoOfJwt = jwtService.createTokenDto(userEntity);
            return DtoOfLoginSuccess.builder()
                    .id(userEntity.getId())
                    .nickName(userEntity.getNickname())
                    .accessToken(dtoOfJwt.getAccessToken())
                    .refreshToken(dtoOfJwt.getRefreshToken())
                    .build();
        }

        // 실제코드
        DtoOfOauthTokenResponse oauthTokenResponse = oauth2Util.getToken(authorizationCode);
        DtoOfUserProfile userProfile = oauth2Util.getUserProfile(oauthTokenResponse.getAccessToken());
        User userEntity = userService.login(userProfile);
        DtoOfJwt dtoOfJwt = jwtService.createTokenDto(userEntity);

        return DtoOfLoginSuccess.builder()
                .id(userEntity.getId())
                .nickName(userEntity.getNickname())
                .accessToken(dtoOfJwt.getAccessToken())
                .refreshToken(dtoOfJwt.getRefreshToken())
                .build();
    }

    private DtoOfUserProfile mockUser(){

        DtoOfUserProfile mock = new DtoOfUserProfile();
        mock.setId(112233112233L);
        DtoOfUserProfile.Properties mockProperties = new DtoOfUserProfile.Properties();
        DtoOfUserProfile.KakaoAccount mockKakaoAccount = new DtoOfUserProfile.KakaoAccount();
        DtoOfUserProfile.Profile mockProfile = new DtoOfUserProfile.Profile();
        mockProfile.setNickname("테스트용아이디");
        mockProperties.setNickname("테스트용아이디");
        mock.setProperties(mockProperties);
        mock.setKakaoAccount(mockKakaoAccount);

        return mock;
    }
}

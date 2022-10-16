package com.example.miraeroback.jwt;

import com.example.miraeroback.jwt.dto.DtoOfJwt;
import com.example.miraeroback.jwt.dto.DtoOfUserDataFromJwt;
import com.example.miraeroback.jwt.util.JwtFactory;
import com.example.miraeroback.jwt.util.JwtProvider;
import com.example.miraeroback.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService{

    private final JwtRepository jwtRepository;
    private final JwtFactory jwtFactory;
    private final JwtProvider jwtProvider;

    // 최초 로그인
    public DtoOfJwt createTokenDto(User user){
        String accessToken = createAccessToken(user);

        String refreshToken = createRefreshToken(accessToken, user);

        return DtoOfJwt.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    private String createAccessToken(User user){
        String accessToken = jwtFactory.createAccessToken(user);
        return accessToken;
    }
    private String createRefreshToken(String accessToken, User user){

        if(!isExistsRefreshToken(accessToken)){
            String refreshToken = jwtFactory.createRefreshToken(accessToken);
            saveRefreshToken(accessToken, refreshToken, user);
            return refreshToken;
        }

        return accessToken;
    }

    public DtoOfUserDataFromJwt getUserData(String accessToken){
        DtoOfUserDataFromJwt dtoOfUserDataFromJwt =
                jwtProvider.getUserData(accessToken);
        return dtoOfUserDataFromJwt;
    }
    public DtoOfJwt refresh(String refreshToken){
        RefreshToken refreshTokenEntity = getRefreshToken(refreshToken);
        //fixme 하드코딩 고쳐야함
        boolean check = jwtProvider.checkRenewRefreshToken(refreshTokenEntity.getRefreshToken(), 3L);
        User user = User.builder()
                .nickname(refreshTokenEntity.getNickname())
                .build();

        if(check){
            DtoOfJwt dtoOfJwt = createTokenDto(user);
            jwtRepository.delete(refreshTokenEntity);

            return dtoOfJwt;
        }

        String renewAccessToken = createAccessToken(user);
        refreshTokenEntity.updateAccessToken(renewAccessToken);

        return DtoOfJwt.builder().
                refreshToken(refreshTokenEntity.getRefreshToken())
                .accessToken(renewAccessToken)
                .build();
    }

    public boolean isExistsRefreshToken(String refreshToken){
        return jwtRepository.existsByRefreshToken(refreshToken);
    }

    public void saveRefreshToken(String accessToken, String refreshToken, User user){

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();

        jwtRepository.save(refreshTokenEntity);
    }
    private RefreshToken getRefreshToken(String refreshToken){
        RefreshToken refreshTokenEntity = jwtRepository.findByRefreshToken(refreshToken).get();

        return refreshTokenEntity;
    }



}

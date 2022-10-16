package com.example.miraeroback.login.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DtoOfLoginSuccess {
    /**
     * 멤버 id
     */
    private Long id;
    /**
     * 자체 발급 엑세스 토큰
     */
    private String accessToken;

    /**
     * 자체 발급 리프레시 토큰
     */
    private String refreshToken;

    /**
     * 사용자의 닉네임
     */
    private String nickName;
}

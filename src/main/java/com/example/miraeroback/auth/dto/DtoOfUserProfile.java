package com.example.miraeroback.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DtoOfUserProfile {

    /**
     * Oauth2로 부터 받은 사용자 id
     */
    private Long id;

    /**
     * 사용자 속성 값
     */
    private Properties properties;

    /**
     * 사용자 데이터
     */
    private KakaoAccount kakaoAccount;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class KakaoAccount {


        private Profile profile;

    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Profile {

        private String nickname;

    }


    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Properties {

        private String nickname;

    }

}

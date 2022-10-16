package com.example.miraeroback.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DtoOfJwt {
    private String accessToken;
    private String refreshToken;
}

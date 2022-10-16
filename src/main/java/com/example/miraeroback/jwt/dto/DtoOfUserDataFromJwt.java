package com.example.miraeroback.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DtoOfUserDataFromJwt {


    private Long id;
    private String nickname;
}

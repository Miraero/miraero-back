package com.example.miraeroback.user;

import com.example.miraeroback.auth.dto.DtoOfUserProfile;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean isFirstLogin(Long kakaoId);
    User login(DtoOfUserProfile dtoOfUserProfile);
}

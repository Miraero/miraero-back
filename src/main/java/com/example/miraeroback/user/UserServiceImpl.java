package com.example.miraeroback.user;

import com.example.miraeroback.auth.dto.DtoOfUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public boolean isFirstLogin(Long kakaoId){

        //최초 로그인 시, true
        boolean isFirst = userRepository.existsByKakaoId(kakaoId);

        //최초 로그인 시, false 리턴
        return !isFirst;
    }
    @Override
    public User login(DtoOfUserProfile dtoOfUserProfile) {



        boolean isFirst = isFirstLogin(dtoOfUserProfile.getId());

        if(isFirst){
            User newMember = User.builder()
                    .nickname(dtoOfUserProfile.getProperties().getNickname())
                    .kakaoId(dtoOfUserProfile.getId())
                    .build();
            User savedMember = userRepository.save(newMember);

            return savedMember;
        }

        User memberEntity = userRepository.findByKakaoId(dtoOfUserProfile.getId()).get();

        return memberEntity;
    }
}

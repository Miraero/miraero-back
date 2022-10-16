package com.example.miraeroback.login;

import com.example.miraeroback.login.dto.DtoOfLoginSuccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {
    @Autowired
    private final LoginService loginService;

    /**
     * 사용자 로그인 메서드
     * @param code : Oauth2에서 제공받은 Authorization Code
     * @return 로그인 성공 후, 응답값
     * @throws JsonProcessingException
     */
    @GetMapping("/login/oauth")
    public ResponseEntity<?> oauth2Login(@RequestParam String code) throws JsonProcessingException {
        DtoOfLoginSuccess loginSuccess = loginService.login(code);
        return new ResponseEntity<>(loginSuccess, HttpStatus.OK);
    }
}

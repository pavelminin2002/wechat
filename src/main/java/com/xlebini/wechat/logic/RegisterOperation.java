package com.xlebini.wechat.logic;

import com.xlebini.wechat.dto.RegisterRequest;
import com.xlebini.wechat.entity.User;
import com.xlebini.wechat.helper.AuthHelper;
import com.xlebini.wechat.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthHelper authHelper;

    public void activate(RegisterRequest registerRequest, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setLogin(registerRequest.getLogin());
        user.setEncodedPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
        log.info("user was registered {}", user);
        authHelper.setAuthentication(user, request, response);
    }
}
package com.xlebini.wechat.service;

import com.xlebini.wechat.entity.User;
import com.xlebini.wechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s was not found", login))
        );
    }
}
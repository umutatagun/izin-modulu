package com.example.demo.service.impl;

import com.example.demo.model.Login;
import com.example.demo.model.dto.LoginDto;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login create(Login employee){
        return loginRepository.save(employee);
    }

    public Login findUserByUsername(String username) {
        return loginRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: "+username));
    }

    public LoginDto getUserDto(String username) {
        Login user = findUserByUsername(username);
        return LoginDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }
}

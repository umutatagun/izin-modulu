package com.example.demo.service;

import com.example.demo.model.Login;
import com.example.demo.model.dto.LoginDto;

public interface LoginService {
    Login create(Login user);
    Login findUserByUsername(String username);
    LoginDto getUserDto(String username);
}

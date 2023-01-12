package com.example.demo.service.impl;

import com.example.demo.model.dto.LoginDto;
import com.example.demo.model.dto.LoginRequestDto;
import com.example.demo.model.dto.TokenResponseDto;
import com.example.demo.service.LoginService;
import com.example.demo.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, LoginService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.loginService = userService;
        this.tokenService = tokenService;
    }

    public TokenResponseDto login(LoginRequestDto loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenService.generateToken(auth))
                    .user(loginService.getUserDto(loginRequest.getUsername())).build();
        }catch (final BadCredentialsException badCredentialsException) {
            throw new RuntimeException("Exc");
        }
    }
}

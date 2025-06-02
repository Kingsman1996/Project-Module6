package com.example.demo.service;

import com.example.demo.dto.LoginForm;
import com.example.demo.dto.RegisterForm;
import com.example.demo.entity.AuthInfo;
import com.example.demo.exception.AuthenException;
import com.example.demo.exception.UsernameExistException;
import com.example.demo.repository.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthInfoService {
    private final AuthInfoRepository authInfoRepository;

    public AuthInfo login(LoginForm loginForm) {
        AuthInfo authInfo = authInfoRepository.findByUsername(loginForm.getUsername());
        if (authInfo == null) {
            throw new AuthenException("Sai tài khoản");
        }
        if (!authInfo.getPassword().equals(loginForm.getPassword())) {
            throw new AuthenException("Sai mật khẩu");
        }
        return authInfo;
    }

    public void register(RegisterForm registerForm) {
        if (authInfoRepository.existsByUsername(registerForm.getUsername())) {
            throw new UsernameExistException("Tài khoản đã tồn tại");
        }
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUsername(registerForm.getUsername());
        authInfo.setPassword(registerForm.getPassword());
        authInfo.setRole(registerForm.getRole());
        authInfoRepository.save(authInfo);
    }

    public AuthInfo findByUsername(String username) {
        return authInfoRepository.findByUsername(username);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.LoginForm;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterForm;
import com.example.demo.dto.RegisterResponse;
import com.example.demo.entity.AuthInfo;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.service.AuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthInfoService authInfoService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        AuthInfo authInfo = authInfoService.login(loginForm);
        String token = jwtUtil.generateToken(authInfo.getUsername(), authInfo.getRole());
        return ResponseEntity.ok(new LoginResponse("Đăng nhập thành công", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) {
        authInfoService.register(registerForm);
        return ResponseEntity.ok(new RegisterResponse("Đăng ký thành công"));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse(null);
        Map<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("role", role);
        return ResponseEntity.ok(result);
    }
}

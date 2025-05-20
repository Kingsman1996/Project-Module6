package com.example.demo.repository;

import com.example.demo.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {
    boolean existsByUsername(String username);
    AuthInfo findByUsername(String username);
}

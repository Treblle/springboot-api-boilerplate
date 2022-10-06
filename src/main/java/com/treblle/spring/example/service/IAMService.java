package com.treblle.spring.example.service;

import com.treblle.spring.example.service.dto.TokenResponse;
import org.springframework.security.core.Authentication;

public interface IAMService {

  TokenResponse authenticate(Authentication authentication);

}

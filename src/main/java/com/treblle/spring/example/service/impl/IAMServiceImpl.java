package com.treblle.spring.example.service.impl;

import com.treblle.spring.example.service.IAMService;
import com.treblle.spring.example.service.dto.TokenResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class IAMServiceImpl implements IAMService {

  private final JwtEncoder encoder;

  public IAMServiceImpl(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public TokenResponse authenticate(Authentication authentication) {
    Instant now = Instant.now();
    long expiry = 36000L;
    String scope = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();
    TokenResponse tokenResponse = new TokenResponse();
    tokenResponse.setToken(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    tokenResponse.setExpiresIn(expiry);
    return tokenResponse;
  }

}

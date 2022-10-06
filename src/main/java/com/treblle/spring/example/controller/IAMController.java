package com.treblle.spring.example.controller;

import com.treblle.spring.example.service.IAMService;
import com.treblle.spring.example.service.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "HTTPBasic")
@Tag(name = "IAM", description = "the IAM API")
@RequestMapping("/api/v1/iam")
public class IAMController {

  private final IAMService iamService;

  @Autowired
  public IAMController(IAMService iamService) {
    this.iamService = iamService;
  }

  @Operation(summary = "Authenticates user and returns JWT token based on supplied HTTP basic credentials")
  @PostMapping("/authenticate")
  public TokenResponse authenticate(Authentication authentication) {
    return iamService.authenticate(authentication);
  }

}

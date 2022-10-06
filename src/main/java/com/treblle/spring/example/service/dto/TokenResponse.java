package com.treblle.spring.example.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse extends Response {

  private String token;

  @JsonProperty("expires_in")
  private Long expiresIn;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
  }

}

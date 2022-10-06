package com.treblle.spring.example.service.dto;

public class UserResponse extends Response {

  private User user;

  public UserResponse() {
  }

  public UserResponse(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}

package com.treblle.spring.example.service.dto;

import java.util.List;

public class UserListResponse extends Response {

  private List<User> users;

  public UserListResponse() {
  }

  public UserListResponse(List<User> users) {
    this.users = users;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

}

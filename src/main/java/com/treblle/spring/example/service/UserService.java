package com.treblle.spring.example.service;

import com.treblle.spring.example.service.dto.User;
import com.treblle.spring.example.service.dto.UserRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
  List<User> getUsers();

  Optional<User> getUser(UUID userUUID);

  User createUser(UserRequest userRequest);

  Optional<User> deleteUser(Authentication authentication, UUID userUUID);

  Optional<User> updateUser(Authentication authentication, UUID userUUID, UserRequest userRequest);

}

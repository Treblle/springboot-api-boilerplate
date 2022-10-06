package com.treblle.spring.example.service.impl;

import com.treblle.spring.example.repository.UserRepository;
import com.treblle.spring.example.repository.entity.UserEntity;
import com.treblle.spring.example.service.UserService;
import com.treblle.spring.example.service.dto.User;
import com.treblle.spring.example.service.dto.UserPrincipal;
import com.treblle.spring.example.service.dto.UserRequest;
import com.treblle.spring.example.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll().stream().map(userMapper::map).collect(Collectors.toList());
  }

  @Override
  public Optional<User> getUser(UUID userUUID) {
    return userRepository.findById(userUUID).map(userMapper::map);
  }

  @Override
  public User createUser(UserRequest userRequest) {
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(userRequest.getEmail());
    userEntity.setName(userRequest.getName());
    userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    UserEntity result = userRepository.saveAndFlush(userEntity);
    return userMapper.map(result);
  }

  @Override
  public Optional<User> deleteUser(Authentication authentication, UUID userUUID) {
    Optional<UserEntity> user = userRepository.findById(userUUID);
    user.ifPresent(it -> {
      if (!authentication.getName().equals(it.getEmail())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are only allowed to delete your own user");
      }
      userRepository.delete(it);
    });
    return user.map(userMapper::map);
  }

  @Override
  public Optional<User> updateUser(Authentication authentication, UUID userUUID, UserRequest userRequest) {
    return userRepository.findById(userUUID).map(it -> {
      if (!authentication.getName().equals(it.getEmail())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are only allowed to update your own posts");
      }
      it.setEmail(userRequest.getEmail());
      it.setName(userRequest.getName());
      it.setPassword(passwordEncoder.encode(userRequest.getPassword()));
      UserEntity result = userRepository.saveAndFlush(it);
      return userMapper.map(result);
    });
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .map(UserPrincipal::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

}

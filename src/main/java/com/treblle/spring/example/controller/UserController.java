package com.treblle.spring.example.controller;

import com.treblle.spring.example.service.PostService;
import com.treblle.spring.example.service.UserService;
import com.treblle.spring.example.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "User", description = "the User API")
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final PostService postService;

  @Autowired
  public UserController(UserService userService, PostService postService) {
    this.userService = userService;
    this.postService = postService;
  }

  @Operation(summary = "Returns list of all users", security = { @SecurityRequirement(name = "JWT") })
  @GetMapping()
  public UserListResponse getUsers() {
    List<User> users = userService.getUsers();
    return new UserListResponse(users);
  }

  @Operation(summary = "Returns requested user", security = { @SecurityRequirement(name = "JWT") })
  @GetMapping("/{user-uuid}")
  public UserResponse getUser(@PathVariable("user-uuid") UUID userUUID) {
    Optional<User> user = userService.getUser(userUUID);
    return user.map(UserResponse::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  @Operation(summary = "Returns requested users posts", security = { @SecurityRequirement(name = "JWT") })
  @GetMapping("/{user-uuid}/posts")
  public PostListResponse getUserPosts(@PathVariable("user-uuid") UUID userUUID) {
    User user = userService.getUser(userUUID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    List<Post> posts = postService.getPosts(user.getId());
    return new PostListResponse(posts);
  }

  @Operation(summary = "Creates new user")
  @PostMapping()
  public UserResponse createUser(@RequestBody UserRequest userRequest) {
    User user = userService.createUser(userRequest);
    return new UserResponse(user);
  }

  @Operation(summary = "Deletes requested user", security = { @SecurityRequirement(name = "JWT") })
  @DeleteMapping("/{user-uuid}")
  public Response deleteUser(Authentication authentication, @PathVariable("user-uuid") UUID userUUID) {
    Optional<User> user = userService.deleteUser(authentication, userUUID);
    return user.map(it -> new Response()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  @Operation(summary = "Updates requested user", security = { @SecurityRequirement(name = "JWT") })
  @PutMapping("/{user-uuid}")
  public UserResponse updateUser(Authentication authentication, @PathVariable("user-uuid") UUID userUUID, @RequestBody UserRequest userRequest) {
    Optional<User> user = userService.updateUser(authentication, userUUID, userRequest);
    return user.map(UserResponse::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

}

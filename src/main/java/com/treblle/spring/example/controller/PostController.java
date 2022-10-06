package com.treblle.spring.example.controller;

import com.treblle.spring.example.service.PostService;
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
@SecurityRequirement(name = "JWT")
@Tag(name = "Post", description = "the Post API")
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @Operation(summary = "Returns list of all posts")
  @GetMapping()
  public PostListResponse getPosts() {
    List<Post> posts = postService.getPosts();
    return new PostListResponse(posts);
  }

  @Operation(summary = "Creates new post")
  @PostMapping()
  public PostResponse createPost(Authentication authentication, @RequestBody PostRequest postRequest) {
    Post post = postService.createPost(authentication, postRequest);
    return new PostResponse(post);
  }

  @Operation(summary = "Deletes requested post")
  @DeleteMapping("/{post-uuid}")
  public Response deletePost(Authentication authentication, @PathVariable("post-uuid") UUID postUUID) {
    Optional<Post> post = postService.deletePost(authentication, postUUID);
    return post.map(it -> new Response()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
  }

  @Operation(summary = "Updates requested post")
  @PutMapping("/{post-uuid}")
  public PostResponse updatePost(Authentication authentication, @PathVariable("post-uuid") UUID postUUID, @RequestBody PostRequest postRequest) {
    Optional<Post> post = postService.updatePost(authentication, postUUID, postRequest);
    return post.map(PostResponse::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
  }

}

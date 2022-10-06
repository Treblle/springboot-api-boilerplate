package com.treblle.spring.example.service;

import com.treblle.spring.example.service.dto.Post;
import com.treblle.spring.example.service.dto.PostRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {

  List<Post> getPosts();

  List<Post> getPosts(UUID postUUID);

  Post createPost(Authentication authentication, PostRequest postRequest);

  Optional<Post> deletePost(Authentication authentication, UUID postUUID);

  Optional<Post> updatePost(Authentication authentication, UUID postUUID, PostRequest postRequest);

}

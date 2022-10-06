package com.treblle.spring.example.service.impl;

import com.treblle.spring.example.repository.PostRepository;
import com.treblle.spring.example.repository.UserRepository;
import com.treblle.spring.example.repository.entity.PostEntity;
import com.treblle.spring.example.repository.entity.UserEntity;
import com.treblle.spring.example.service.PostService;
import com.treblle.spring.example.service.dto.Post;
import com.treblle.spring.example.service.dto.PostRequest;
import com.treblle.spring.example.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final UserRepository userRepository;


  @Autowired
  public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.postMapper = postMapper;
    this.userRepository = userRepository;
  }

  @Override
  public List<Post> getPosts() {
    return postRepository.findAll().stream().map(postMapper::map).collect(Collectors.toList());
  }

  @Override
  public List<Post> getPosts(UUID postUUID) {
    return postRepository.findAllById(postUUID).stream()
        .map(postMapper::map)
        .collect(Collectors.toList());
  }

  @Override
  public Post createPost(Authentication authentication, PostRequest postRequest) {
    UserEntity user = userRepository.findByEmail(authentication.getName())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    PostEntity postEntity = new PostEntity();
    postEntity.setUser(userRepository.getReferenceById(user.getId()));
    postEntity.setTitle(postRequest.getTitle());
    postEntity.setContent(postRequest.getContent());
    PostEntity result = postRepository.saveAndFlush(postEntity);
    return postMapper.map(result);
  }

  @Override
  public Optional<Post> deletePost(Authentication authentication, UUID postUUID) {
    Optional<PostEntity> post = postRepository.findById(postUUID);
    post.ifPresent(it -> {
      if (!authentication.getName().equals(it.getUser().getEmail())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are only allowed to delete your own posts");
      }
      postRepository.delete(it);
    });
    return post.map(postMapper::map);
  }

  @Override
  public Optional<Post> updatePost(Authentication authentication, UUID postUUID, PostRequest postRequest) {
    return postRepository.findById(postUUID).map(it -> {
      if (!authentication.getName().equals(it.getUser().getEmail())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are only allowed to update your own posts");
      }
      it.setTitle(postRequest.getTitle());
      it.setContent(postRequest.getContent());
      PostEntity result = postRepository.saveAndFlush(it);
      return postMapper.map(result);
    });
  }

}

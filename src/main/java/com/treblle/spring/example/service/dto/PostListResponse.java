package com.treblle.spring.example.service.dto;

import java.util.List;

public class PostListResponse extends Response {

  private List<Post> posts;

  public PostListResponse() {
  }

  public PostListResponse(List<Post> posts) {
    this.posts = posts;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

}

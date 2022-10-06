package com.treblle.spring.example.service.dto;

public class PostResponse extends Response {

  private Post post;

  public PostResponse() {
  }

  public PostResponse(Post post) {
    this.post = post;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

}

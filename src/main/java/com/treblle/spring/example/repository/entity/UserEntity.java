package com.treblle.spring.example.repository.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="users")
public class UserEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name="id", updatable = false, nullable = false)
  private UUID id;

  @Column(name="name")
  private String name;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @OneToMany(mappedBy="user")
  private Set<PostEntity> posts;

  @CreationTimestamp
  @Column(name="created_at")
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(name="updated_at")
  private OffsetDateTime updatedAt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<PostEntity> getPosts() {
    return posts;
  }

  public void setPosts(Set<PostEntity> posts) {
    this.posts = posts;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}

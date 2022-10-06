package com.treblle.spring.example.service.mapper;

import com.treblle.spring.example.repository.entity.PostEntity;
import com.treblle.spring.example.service.dto.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

  Post map(PostEntity postEntity);

}

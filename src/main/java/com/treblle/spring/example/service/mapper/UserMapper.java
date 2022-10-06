package com.treblle.spring.example.service.mapper;

import com.treblle.spring.example.repository.entity.UserEntity;
import com.treblle.spring.example.service.dto.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User map(UserEntity userEntity);

}

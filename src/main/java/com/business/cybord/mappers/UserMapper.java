package com.business.cybord.mappers;

import org.mapstruct.Mapper;

import com.business.cybord.entities.User;
import com.business.cybord.models.dtos.UserDto;

@Mapper
public interface UserMapper {
	
	User getEntityFromUserDto(UserDto dto);
	UserDto getDtoFromUserEntity(User dto);

}

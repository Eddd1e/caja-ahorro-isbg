package com.business.cybord.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.cybord.entities.User;
import com.business.cybord.mappers.UserMapper;
import com.business.cybord.models.dtos.UserDto;
import com.business.cybord.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserMapper mapper;

	public UserDto getUserById(Integer id) {
		User entity = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El usuario no existe, id: %d", id)));
		return mapper.getDtoFromUserEntity(entity);
	}
	
	public UserDto createUser(UserDto user) {
		Optional <User> entity = repository.findById(user.getId());
		if(!entity.isPresent()) {
			return mapper.getDtoFromUserEntity(repository.save(mapper.getEntityFromUserDto(user)));
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El usuario ya existe, id: %d", user.getId()));
		}
	}
	public UserDto updateUser(UserDto user) {
		User entity = repository.findById(user.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El usuario no existe, id: %d", user.getId())));
		entity.setNombre(user.getNombre());
		entity.setNickname(user.getNickname());
		entity.setActivo(user.getActivo());
		entity.setFechaActualizacion(user.getFechaActualizacion());
		return mapper.getDtoFromUserEntity(entity);
	}
	
	public void deleteUserById(Integer id) {
		User entity = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El usuario no existe, id: %d", id)));
		repository.delete(entity);
	}
}

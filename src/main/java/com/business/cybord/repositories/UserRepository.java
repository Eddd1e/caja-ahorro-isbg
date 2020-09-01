package com.business.cybord.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.business.cybord.entities.User;


@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {

}

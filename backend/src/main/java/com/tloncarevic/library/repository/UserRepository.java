package com.tloncarevic.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.tloncarevic.library.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

}
package com.programar.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.programar.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
}

package com.bridgelabz.fundonotes.module.service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundonotes.module.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
public Optional<User> findByEmail(String email);
}

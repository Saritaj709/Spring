package com.bridgelabz.login.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

}

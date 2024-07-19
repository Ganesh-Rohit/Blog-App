package com.travel.blog.Dao;

import com.travel.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmailId(String emailId);
}

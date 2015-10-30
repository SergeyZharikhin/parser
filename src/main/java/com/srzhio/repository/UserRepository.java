package com.srzhio.repository;

import com.srzhio.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    public Optional <User> findByUsername(String username);

}



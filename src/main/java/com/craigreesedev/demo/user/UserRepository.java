package com.craigreesedev.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);

    List<User> findAll();

    Optional<User> findById(Long id);
}

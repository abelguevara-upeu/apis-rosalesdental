package com.rosalesdentalcare.dental_platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.rosalesdentalcare.dental_platform.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
    Optional<User> findByUsername(String username);


}

package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.config.User;

public interface UserRepository extends JpaRepository<User, Long> {}

package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}

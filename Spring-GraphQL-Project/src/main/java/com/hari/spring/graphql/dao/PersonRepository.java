package com.hari.spring.graphql.dao;

import org.springframework.data.repository.CrudRepository;

import com.hari.spring.graphql.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	Person findByEmail(String email);

	
}

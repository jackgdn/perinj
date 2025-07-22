package com.jackgdn.perinj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jackgdn.perinj.entity.Person;

public interface PerinjRepository extends MongoRepository<Person, String> {

}

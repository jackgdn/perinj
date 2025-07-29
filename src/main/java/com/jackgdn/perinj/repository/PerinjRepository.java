package com.jackgdn.perinj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jackgdn.perinj.entity.Person;

@Repository
public interface PerinjRepository extends MongoRepository<Person, String> {

}

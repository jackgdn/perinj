package com.jackgdn.perinj.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "persons")
public class Person {
    @Id
    private String name;
    private String dateOfBirth;
    private List<String> phoneNumbers;
    private List<String> emailAddresses;
    private String qq;
    private String studentID;
    private String id;
    private String address;
    private List<PasswordSet> passwordSets;
    private Map<String, String> otherInformation;
}

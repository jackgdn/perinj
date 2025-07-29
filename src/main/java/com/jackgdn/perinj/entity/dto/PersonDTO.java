package com.jackgdn.perinj.entity.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonDTO {
    @NotBlank
    private String name;
    private String dateOfBirth;
    private List<String> phoneNumbers;
    private List<@Email String> emailAddresses;
    private String qq;
    private String studentID;
    private String id;
    private String address;
}

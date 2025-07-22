package com.jackgdn.perinj.entity.dto;

import java.util.List;
import java.util.Map;

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
    private String studentId;
    private String ID;
    private String address;
    private List<PasswordSetDTO> passwordSets;
    private List<Map<String, String>> otherInformation;
}

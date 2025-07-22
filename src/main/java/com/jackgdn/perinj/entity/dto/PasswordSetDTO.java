package com.jackgdn.perinj.entity.dto;

import jakarta.validation.constraints.NotBlank;

public class PasswordSetDTO {
    @NotBlank
    private String site;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String expiredPassword;
}

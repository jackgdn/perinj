package com.jackgdn.perinj.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordSetDTO {
    @NotBlank
    private String site;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Boolean expired = false;
}

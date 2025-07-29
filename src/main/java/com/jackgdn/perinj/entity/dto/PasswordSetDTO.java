package com.jackgdn.perinj.entity.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordSetDTO {
    @NotBlank
    private String name;
    private List<PasswordSetSubDTO> passwordSets;
}

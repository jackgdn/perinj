package com.jackgdn.perinj.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InformationDTO {
    @NotBlank
    private String key;
    @NotBlank
    private String value;
}

package com.jackgdn.perinj.entity.dto;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtherInformationDTO {
    @NotBlank
    private String name;
    @NotBlank
    private Map<String, String> otherInformation;
}

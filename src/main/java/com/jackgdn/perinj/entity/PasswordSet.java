package com.jackgdn.perinj.entity;

import lombok.Data;

@Data
public class PasswordSet {
    private String site;
    private String username;
    private String password;
    private Boolean expired;
}

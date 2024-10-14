package com.example.userservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {
    private Long userID;
    private String token;
}

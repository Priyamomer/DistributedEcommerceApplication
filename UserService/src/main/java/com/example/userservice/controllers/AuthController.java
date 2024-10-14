package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.SignupRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.dtos.ValidateTokenRequestDto;
import com.example.userservice.exception.InvalidParameterException;
import com.example.userservice.exception.InvalidRequestException;
import com.example.userservice.models.LogoutRequestDto;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    @Autowired
    private  AuthController (AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws InvalidParameterException {
        System.out.println("Signup user is been triggered");
        return authService.signUp(signupRequestDto.getEmail(),signupRequestDto.getPassword());
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login (@RequestBody LoginRequestDto loginRequest) throws InvalidRequestException, InvalidParameterException {
        return authService.login(loginRequest.getEmail(),loginRequest.getPassword());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) throws InvalidParameterException {
        return authService.logout(logoutRequestDto.getUserID(),logoutRequestDto.getToken());
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto request) throws InvalidParameterException {
        System.out.println("INVOKED "+request.getToken()+" USER_ID "+request.getUserId());
        return authService.validateToken(request.getUserId(),request.getToken());
    }
}


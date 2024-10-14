package com.example.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hi")
public class HiController {
    @GetMapping
    public String message(){
        return "HI THE SERVER IS RUNNING";
    }
}

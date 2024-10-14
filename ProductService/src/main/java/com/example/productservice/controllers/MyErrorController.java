package com.example.productservice.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<h1>Something went wrong! </h1>\n" +
                "<h2>Our Engineers are on it</h2>\n" +
                "<a href=\"/\">Go Home</a>\n" +
                "</body>\n" +
                "</html>";
    }
}
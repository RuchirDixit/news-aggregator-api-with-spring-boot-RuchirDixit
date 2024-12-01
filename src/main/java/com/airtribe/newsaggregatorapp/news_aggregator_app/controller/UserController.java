package com.airtribe.newsaggregatorapp.news_aggregator_app.controller;

import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.UserDTO;
import com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling.UserAlreadyExistsException;
import com.airtribe.newsaggregatorapp.news_aggregator_app.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDto) {
        String returnMessage = "";
        try{
            if(userService.register(userDto).equals("User Registration Successful")) {
                return ResponseEntity.ok("User Registration Successful");
            }
        }
        catch (UserAlreadyExistsException | ConstraintViolationException e){
            returnMessage = e.getMessage();
        }

        return ResponseEntity.badRequest().body(returnMessage);
    }
}

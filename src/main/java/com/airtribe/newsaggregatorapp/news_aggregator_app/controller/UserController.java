package com.airtribe.newsaggregatorapp.news_aggregator_app.controller;

import com.airtribe.newsaggregatorapp.news_aggregator_app.config.JWTUtil;
import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.LoginRequestDTO;
import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.UserDTO;
import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.UserPreferenceDTO;
import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.Users;
import com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling.UserAlreadyExistsException;
import com.airtribe.newsaggregatorapp.news_aggregator_app.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

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

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginRequestDTO userLoginRequestDTO) {
        Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword())
            );
        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/preferences")
    public ResponseEntity<List<String>> getPreferences(@RequestHeader("Authorization")String authorizationHeader) {
        // Extract token from Bearer Token value passed
        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        List<String> preferences = new ArrayList<>(userService.fetchNewsPreferenceOfUser(username));
        return ResponseEntity.ok(preferences);
    }

    @PutMapping("/preferences")
    public ResponseEntity<Users> updatePreferences(@RequestHeader("Authorization")String authorizationHeader, @RequestBody UserPreferenceDTO userPreferenceDTO) {
        // Extract token from Bearer Token value passed
        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        Users users = userService.updateUsersNewsPreference(username, userPreferenceDTO.getCategories());
        return ResponseEntity.ok(users);
    }
}

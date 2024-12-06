package com.airtribe.newsaggregatorapp.news_aggregator_app.controller;

import com.airtribe.newsaggregatorapp.news_aggregator_app.config.JWTUtil;
import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.*;
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

    // API to register User
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

    // API to login the user and generate JWT token
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginRequestDTO userLoginRequestDTO) {
        Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword())
            );
        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(token);
    }

    // Get news preference of user
    @GetMapping("/preferences")
    public ResponseEntity<List<String>> getPreferences(@RequestHeader("Authorization")String authorizationHeader) {
        // Extract token from Bearer Token value passed
        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        List<String> preferences = new ArrayList<>(userService.fetchNewsPreferenceOfUser(username));
        return ResponseEntity.ok(preferences);
    }

    // Update news preference of user
    @PutMapping("/preferences")
    public ResponseEntity<Users> updatePreferences(@RequestHeader("Authorization")String authorizationHeader, @RequestBody UserPreferenceDTO userPreferenceDTO) {
        // Extract token from Bearer Token value passed
        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        Users users = userService.updateUsersNewsPreference(username, userPreferenceDTO.getCategories());
        return ResponseEntity.ok(users);
    }

    // Fetch news article from Gnews api on basis of preferences set by User
    @GetMapping("/news")
    public ResponseEntity<UsersNewsPreferenceOutput> getNewsByUserPreferences(@RequestHeader("Authorization")String authorizationHeader) {
        // Extract token from Bearer Token value passed
        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        NewsResponse newsResponse = userService.fetchNews(username);
        // Prepare an appropriate response with username and articles
        UsersNewsPreferenceOutput usersNewsPreferenceOutput = new UsersNewsPreferenceOutput();
        usersNewsPreferenceOutput.setUsername(username);
        usersNewsPreferenceOutput.setArticles(newsResponse.getArticles());
        return ResponseEntity.ok(usersNewsPreferenceOutput);
    }
}

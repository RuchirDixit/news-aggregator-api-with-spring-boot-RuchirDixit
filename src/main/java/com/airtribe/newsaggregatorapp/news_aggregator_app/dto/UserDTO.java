package com.airtribe.newsaggregatorapp.news_aggregator_app.dto;

import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.NewsPreference;

import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private List<String> preferences;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPreferences() {
        return preferences;
    }
}

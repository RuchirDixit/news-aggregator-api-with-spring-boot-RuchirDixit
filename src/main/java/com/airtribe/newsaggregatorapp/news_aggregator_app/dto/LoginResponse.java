package com.airtribe.newsaggregatorapp.news_aggregator_app.dto;

// response Body of login api
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}

package com.airtribe.newsaggregatorapp.news_aggregator_app.dto;

import java.util.List;

// Response Body of fetch news api
public class UsersNewsPreferenceOutput {
    private String username;
    private List<Article> articles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

package com.airtribe.newsaggregatorapp.news_aggregator_app.dto;

import java.util.List;

// Request Body of preferences api to update users preferences
public class UserPreferenceDTO {
    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}

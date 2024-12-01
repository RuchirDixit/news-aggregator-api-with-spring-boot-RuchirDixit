package com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

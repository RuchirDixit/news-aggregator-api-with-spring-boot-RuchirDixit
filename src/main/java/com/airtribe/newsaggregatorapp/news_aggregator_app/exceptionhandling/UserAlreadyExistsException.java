package com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling;

public class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

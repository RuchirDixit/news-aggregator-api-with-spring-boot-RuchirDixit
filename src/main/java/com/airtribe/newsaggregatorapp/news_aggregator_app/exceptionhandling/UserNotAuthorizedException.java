package com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling;

public class UserNotAuthorizedException extends Exception {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}

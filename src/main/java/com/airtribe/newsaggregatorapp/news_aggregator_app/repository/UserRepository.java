package com.airtribe.newsaggregatorapp.news_aggregator_app.repository;

import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    public Users findByEmail(String email);
}

package com.airtribe.newsaggregatorapp.news_aggregator_app.repository;

import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.NewsPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsPreferenceRepository extends JpaRepository<NewsPreference, Long> {
}

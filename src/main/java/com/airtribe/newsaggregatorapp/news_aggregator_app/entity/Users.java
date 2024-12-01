package com.airtribe.newsaggregatorapp.news_aggregator_app.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<NewsPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<NewsPreference> preferences) {
        this.preferences = preferences;
    }

    private String email;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NewsPreference> preferences;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package com.airtribe.newsaggregatorapp.news_aggregator_app.service;

import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.UserDTO;
import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.NewsPreference;
import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.Users;
import com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling.UserAlreadyExistsException;
import com.airtribe.newsaggregatorapp.news_aggregator_app.repository.NewsPreferenceRepository;
import com.airtribe.newsaggregatorapp.news_aggregator_app.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsPreferenceRepository newsPreferenceRepository;


    public String register(UserDTO userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(convertUserDtoToUsersEntity(userDto));
        return "User Registration Successful";
    }

    private Users convertUserDtoToUsersEntity(UserDTO userDto) {
        Users users = new Users();
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setUsername(userDto.getUsername());
        List<NewsPreference> preferenceList = new ArrayList<>();
        // save users news preference in separate table mapped with user_id
        for(String preferences : userDto.getPreferences()){
            NewsPreference newsPreference = new NewsPreference();
            newsPreference.setUsers(users);
            newsPreference.setCategory(preferences);
            preferenceList.add(newsPreference);
            //newsPreferenceRepository.save(newsPreference);
        }
        users.setPreferences(preferenceList);
        return users;
    }
}

package com.airtribe.newsaggregatorapp.news_aggregator_app.service;

import com.airtribe.newsaggregatorapp.news_aggregator_app.dto.UserDTO;
import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.NewsPreference;
import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.Users;
import com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling.UserAlreadyExistsException;
import com.airtribe.newsaggregatorapp.news_aggregator_app.repository.NewsPreferenceRepository;
import com.airtribe.newsaggregatorapp.news_aggregator_app.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsPreferenceRepository newsPreferenceRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder){

        this.passwordEncoder = passwordEncoder;
    }


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
        users.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
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

    public List<String> fetchNewsPreferenceOfUser(String username) {
        Users user = userRepository.findByUsername(username);
        List<String> preferences = new ArrayList<>();
        user.getPreferences().forEach(preference -> preferences.add(preference.getCategory()));
        return preferences;
    }

    public Users updateUsersNewsPreference(String username, List<String> categories) {
        Users user = userRepository.findByUsername(username);
        if(user.getPreferences()!=null || !user.getPreferences().isEmpty()){
            // Detach preferences from the user entity
            for (NewsPreference preference : user.getPreferences()) {
                preference.setUsers(null);
            }
        }
        // delete all existing preferences of user
        newsPreferenceRepository.deleteAll(user.getPreferences());
        user.getPreferences().clear();
        List<NewsPreference> preferenceList = new ArrayList<>();
        // update users preference by new preference passed in categories
        for(String newPreferences : categories){
            NewsPreference newsPreference = new NewsPreference();
            newsPreference.setUsers(user);
            newsPreference.setCategory(newPreferences);
            preferenceList.add(newsPreference);
        }
        user.setPreferences(preferenceList);
        userRepository.save(user);
        return user;
    }
}

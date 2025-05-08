package com.example.subscriptionservice.service;

import com.example.subscriptionservice.model.Subscriptions;
import com.example.subscriptionservice.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    User updateUserById(Long id, User user);

    void deleteUserById(Long id);

    List<User> getUsers();

    void addUserSubscription(Long id, String name);

    Set<Subscriptions> getUserSubscriptions(Long id);

    void removeUserSubscription(Long id, Long subId);
}

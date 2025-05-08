package com.example.subscriptionservice.service.impl;

import com.example.subscriptionservice.model.Subscriptions;
import com.example.subscriptionservice.model.User;
import com.example.subscriptionservice.repository.SubscriptionRepository;
import com.example.subscriptionservice.repository.UserRepository;
import com.example.subscriptionservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UserServiceImpl(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public User createUser(User user) {
        log.info("Создан пользователь с id: {}", user.getId());

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public User updateUserById(Long id, User user) {
        User existingUser = getUserById(id);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Удален пользователь с id: {}", id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUserSubscription(Long userId, String subscriptionName) {

        User user = getUserById(userId);
        Subscriptions subscriptions = subscriptionRepository.findByName(subscriptionName)
                .orElseGet(() -> {

                    Subscriptions sub = new Subscriptions();
                    sub.setName(subscriptionName);

                    return subscriptionRepository.save(sub);
                });

        user.getSubscriptions().add(subscriptions);
        userRepository.save(user);
        subscriptionRepository.save(subscriptions);
    }

    @Override
    public Set<Subscriptions> getUserSubscriptions(Long id) {
        return getUserById(id).getSubscriptions();
    }

    @Override
    public void removeUserSubscription(Long userId, Long subscriptionId) {
        User user = getUserById(userId);
        user.getSubscriptions().removeIf(sub -> sub.getId().equals(subscriptionId));
        userRepository.save(user);
    }
}

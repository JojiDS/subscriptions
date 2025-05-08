package com.example.subscriptionservice.controller;

import com.example.subscriptionservice.model.Subscriptions;
import com.example.subscriptionservice.model.User;
import com.example.subscriptionservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/{id}/subscriptions")
    public void addSubscription(@PathVariable Long id, @RequestParam String name) {
        userService.addUserSubscription(id, name);
    }

    @GetMapping("/{id}/subscriptions")
    public Set<Subscriptions> getSubs(@PathVariable Long id) {
        return userService.getUserSubscriptions(id);
    }

    @DeleteMapping("/{id}/subscriptions/{subId}")
    public void removeSub(@PathVariable Long id, @PathVariable Long subId) {
        userService.removeUserSubscription(id, subId);
    }
}

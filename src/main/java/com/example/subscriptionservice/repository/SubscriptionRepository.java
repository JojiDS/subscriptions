package com.example.subscriptionservice.repository;

import com.example.subscriptionservice.model.Subscriptions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {

    Optional<Subscriptions> findByName(String name);

    @Query("SELECT s.name FROM Subscriptions s JOIN s.users u GROUP BY s.name ORDER BY COUNT(u.id) DESC")
    List<String> findTopSubscriptions(Pageable pageable);

}

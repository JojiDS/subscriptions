package com.example.subscriptionservice.service.impl;

import com.example.subscriptionservice.repository.SubscriptionRepository;
import com.example.subscriptionservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<String> getTopSubscriptions() {
        return subscriptionRepository.findTopSubscriptions(PageRequest.of(0, 3));
    }
}

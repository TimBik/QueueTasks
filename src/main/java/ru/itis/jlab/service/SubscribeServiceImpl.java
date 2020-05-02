package ru.itis.jlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.jlab.model.Subscriber;
import ru.itis.jlab.repository.SubscriberRepository;


public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    SubscriberRepository subscriberRepository;

    @Override
    public void subscribe(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }
}

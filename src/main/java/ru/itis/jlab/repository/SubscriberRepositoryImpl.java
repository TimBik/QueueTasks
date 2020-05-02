package ru.itis.jlab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jlab.model.Subscriber;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SubscriberRepositoryImpl implements SubscriberRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Optional<Subscriber> find(String s) {
        return Optional.empty();
    }

    @Override
    public List<Subscriber> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void save(Subscriber entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void update(Subscriber entity) {

    }
}

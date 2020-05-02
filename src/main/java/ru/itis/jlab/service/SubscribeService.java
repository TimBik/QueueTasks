package ru.itis.jlab.service;

import org.springframework.web.socket.WebSocketSession;
import ru.itis.jlab.dto.TaskDto;
import ru.itis.jlab.model.Subscriber;
import ru.itis.jlab.model.Task;
import ru.itis.jlab.model.User;

public interface SubscribeService {
    void subscribe(Subscriber subscriber);
}

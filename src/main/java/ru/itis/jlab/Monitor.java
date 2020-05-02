package ru.itis.jlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.jlab.dto.TaskDto;
import ru.itis.jlab.model.QueueTask;
import ru.itis.jlab.model.Subscriber;
import ru.itis.jlab.model.Task;
import ru.itis.jlab.service.QueueTaskService;
import ru.itis.jlab.service.SubscribeService;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class Monitor {
    @Autowired
    QueueTaskService queueTaskService;

    @Autowired
    SubscribeService subscribeService;

    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        try {
            while (true) {
                Optional<Task> optionalTask = queueTaskService.getRandomTask();
                while (!optionalTask.isPresent()) {
                    this.wait();
                    optionalTask = queueTaskService.getRandomTask();
                }
                Task task = optionalTask.get();
                String queueName = task.getQueueTask().getQueueName();
                Optional<Subscriber> optionalSubscriber = subscribeService.getRandomSubscriberByQueueName(queueName);
                while (!optionalSubscriber.isPresent()) {
                    this.wait();
                    optionalSubscriber = subscribeService.getRandomSubscriberByQueueName(queueName);
                }
                Subscriber subscriber = optionalSubscriber.get();
                TaskDto taskDto = TaskDto.builder()
                        .body(task.getBodyJson())
                        .command(task.getCommand())
                        .id(task.getId())
                        .queueName(queueName)
                        .build();
                subscriber.getWebSocketSession().sendMessage(taskDto);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
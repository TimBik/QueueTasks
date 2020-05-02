package ru.itis.jlab.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.sockjs.client.WebSocketClientSockJsSession;
import ru.itis.jlab.dto.MessageDto;
import ru.itis.jlab.dto.TaskDto;
import ru.itis.jlab.model.QueueTask;
import ru.itis.jlab.model.Subscriber;
import ru.itis.jlab.model.Task;
import ru.itis.jlab.service.QueueTaskService;
import ru.itis.jlab.service.SubscribeService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private QueueTaskService queueTaskService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        TaskDto taskFromClient = objectMapper.readValue(messageText, TaskDto.class);

        if (sessions.containsKey(session.getId())) {
            //обработка статусов
        } else {
            sessions.put(session.getId(), session);
            if (taskFromClient.getCommand().equals("subscribe")) {
                QueueTask queueTask = QueueTask.builder().queueName(taskFromClient.getQueueName()).build();
                Subscriber subscriber = Subscriber.builder()
                        .queueTask(queueTask)
                        .webSocketSession(session)
                        .build();
                subscribeService.subscribe(subscriber);
            } else {
                Optional<QueueTask> optionalQueueTask = queueTaskService.findByQueueName(taskFromClient.getQueueName());
                if (!optionalQueueTask.isPresent()) {
                    queueTaskService.createNewQueue(taskFromClient.getQueueName());
                }
                QueueTask queueTask = QueueTask.builder().queueName(taskFromClient.getQueueName()).build();
                Task task = Task.builder()
                        .queueTask(queueTask)
                        .bodyJson(taskFromClient.getBody())
                        .command(taskFromClient.getCommand())
                        .id(UUID.randomUUID().toString())
                        .build();
                queueTaskService.addTask(task);
            }
        }
    }
}
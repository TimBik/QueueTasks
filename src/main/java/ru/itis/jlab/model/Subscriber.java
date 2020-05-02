package ru.itis.jlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    WebSocketSession webSocketSession;
    QueueTask queueTask;
}

package ru.itis.jlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QueueTask {
    String queueName;
    List<Subscriber> subscribers;
    List<Task> tasks;
}

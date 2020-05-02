package ru.itis.jlab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskDto {
    String command;
    String id;
    String queueName;
    String body;
}

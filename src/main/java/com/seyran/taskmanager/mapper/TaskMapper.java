package com.seyran.taskmanager.mapper;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Task;

public class TaskMapper {
    public static Task toEntity(TaskDto  taskDto){
        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .build();

    }
    public static TaskDto toDto(Task  task){
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}

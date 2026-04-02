package com.seyran.taskmanager.mapper;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public static Task toEntity(TaskDto  taskDto){
        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .build();

    }
    public TaskDto toDto(Task task){
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
    public void updateEntity(Task task, TaskDto dto){
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
    }
}

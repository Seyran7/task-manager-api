package com.seyran.taskmanager.service;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Task;
import com.seyran.taskmanager.mapper.TaskMapper;
import com.seyran.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    public List<TaskDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toDto)
                .toList();
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
    public TaskDto createTask(TaskDto taskDto) {
        Task task = TaskMapper.toEntity(taskDto);
        return TaskMapper.toDto(taskRepository.save(task));
    }
    public TaskDto getById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        return TaskMapper.toDto(task);
    }

}

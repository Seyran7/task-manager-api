package com.seyran.taskmanager.service;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Task;
import com.seyran.taskmanager.mapper.TaskMapper;
import com.seyran.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = TaskMapper.toEntity(taskDto);
        return TaskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return TaskMapper.toDto(task);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(taskDto.getStatus());
        task.setDescription(taskDto.getDescription());
        task.setTitle(taskDto.getTitle());
        return TaskMapper.toDto(taskRepository.save(task));
    }
    public Page<TaskDto>getAll(Pageable pageable){
        return taskRepository.findAll(pageable).map(TaskMapper::toDto);
    }
}

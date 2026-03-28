package com.seyran.taskmanager.service;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.entity.Task;
import com.seyran.taskmanager.mapper.TaskMapper;
import com.seyran.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private TaskDto convertToDto(Task task) {
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    public Page<TaskDto> getByStatus(Status status,Pageable pageable) {
        return taskRepository.findByStatus(status,pageable)
                .map(this::convertToDto);
    }

    public Page<TaskDto> searchByTitle(String title, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCase(title,pageable)
                .map(this::convertToDto);
    }
    public List<TaskDto> getSortedTasks(String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        return taskRepository.findAll(sort).stream()
                .map(this::convertToDto)
                .toList();
    }
}

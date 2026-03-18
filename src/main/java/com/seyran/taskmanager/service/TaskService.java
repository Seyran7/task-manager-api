package com.seyran.taskmanager.service;

import com.seyran.taskmanager.entity.Task;
import com.seyran.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

}

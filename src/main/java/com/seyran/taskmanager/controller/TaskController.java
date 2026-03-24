package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }
    @PutMapping
    public ResponseEntity<TaskDto> updateTask( @PathVariable Long id, @RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }
    @GetMapping("/id")
    public ResponseEntity<TaskDto> getById(@RequestParam Long id){
        return ResponseEntity.ok(taskService.getById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@RequestParam Long id){
        taskService.deleteTask(id);
    }
    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable){
        return ResponseEntity.ok(taskService.getAll(pageable));
    }

}

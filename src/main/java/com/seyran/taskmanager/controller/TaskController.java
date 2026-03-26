package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.entity.Task;
import com.seyran.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Task API,description=Operations for tasks")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary="Create task")
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @Operation(summary="Get all tasks")
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }
    @Operation(summary="Uptade task")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask( @PathVariable Long id, @RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }
    @GetMapping("/id")
    public ResponseEntity<TaskDto> getById(@RequestParam Long id){
        return ResponseEntity.ok(taskService.getById(id));
    }
    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public void deleteTask(@RequestParam Long id){
        taskService.deleteTask(id);
    }
    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable){
        return ResponseEntity.ok(taskService.getAll(pageable));
    }
    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam Status status) {
        return taskService.getByStatus(status);
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String title) {
        return taskService.searchByTitle(title);
    }

}

package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Status;
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

@Tag(name = "Task API", description = "Operations for tasks")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @Operation(summary = "Create task")
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @Operation(summary = "Get all tasks with pagination")
    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }


    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id,
                                              @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/status")
    public ResponseEntity<Page<TaskDto>> getTasksByStatus(
            @RequestParam Status status,
            Pageable pageable) {

        return ResponseEntity.ok(taskService.getByStatus(status, pageable));
    }


    @GetMapping("/search")
    public ResponseEntity<Page<TaskDto>> searchTasks(
            @RequestParam String title,
            Pageable pageable) {

        return ResponseEntity.ok(taskService.searchByTitle(title, pageable));
    }


    @GetMapping("/sorted")
    public List<TaskDto> getSortedTasks(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return taskService.getSortedTasks(sortBy, direction);
    }
}
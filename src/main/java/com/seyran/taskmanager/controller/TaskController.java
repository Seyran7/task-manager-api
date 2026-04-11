package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.ApiResponse;
import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<ApiResponse<TaskDto>> createTask(@RequestBody TaskDto taskDto){
        TaskDto created = taskService.createTask(taskDto);

        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task created successfully")
                        .data(created)
                        .build()
        );
    }

    @Operation(summary = "Get all tasks with pagination")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaskDto>>> getAllTasks(Pageable pageable){
        Page<TaskDto> tasks = taskService.getAll(pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<TaskDto>>builder()
                        .success(true)
                        .message("Tasks fetched successfully")
                        .data(tasks)
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> getById(@PathVariable Long id){
        TaskDto task = taskService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task found")
                        .data(task)
                        .build()
        );
    }


    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDto taskDto) {

        TaskDto updated = taskService.updateTask(id, taskDto);

        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Task deleted successfully")
                        .data(null)
                        .build()
        );
    }


    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Page<TaskDto>>> getTasksByStatus(
            @RequestParam Status status,
            Pageable pageable) {

        Page<TaskDto> tasks = taskService.getByStatus(status, pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<TaskDto>>builder()
                        .success(true)
                        .message("Tasks filtered by status")
                        .data(tasks)
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<TaskDto>>> searchTasks(
            @RequestParam String title,
            Pageable pageable) {

        Page<TaskDto> tasks = taskService.searchByTitle(title, pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<TaskDto>>builder()
                        .success(true)
                        .message("Search results")
                        .data(tasks)
                        .build()
        );
    }


    @GetMapping("/sorted")
    public ResponseEntity<ApiResponse<List<TaskDto>>> getSortedTasks(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        List<TaskDto> tasks = taskService.getSortedTasks(sortBy, direction);

        return ResponseEntity.ok(
                ApiResponse.<List<TaskDto>>builder()
                        .success(true)
                        .message("Tasks sorted successfully")
                        .data(tasks)
                        .build()
        );
    }
    @GetMapping("/prefix")
    public ResponseEntity<List<TaskDto>> getByPrefix(@RequestParam String prefix) {
        return ResponseEntity.ok(taskService.getTasksByPrefix(prefix));
    }
    @GetMapping("/count-by-status")
    public ResponseEntity<Long> countByStatus(@RequestParam Status status) {
        return ResponseEntity.ok(taskService.countByStatus(status));
    }
}
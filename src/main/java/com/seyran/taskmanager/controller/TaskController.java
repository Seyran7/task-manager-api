package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.ApiResponse;
import com.seyran.taskmanager.dto.AuthResponse;
import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.RefreshToken;
import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.service.TaskService;
import com.seyran.taskmanager.service.RefreshTokenService;
import com.seyran.taskmanager.security.JwtService;
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
    public ResponseEntity<ApiResponse<TaskDto>> createTask(@RequestBody TaskDto taskDto){
        TaskDto created = taskService.createTask(taskDto);

        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task created")
                        .data(created)
                        .build()
        );
    }

    // ✅ FIX: Pageable
    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaskDto>>> getAllTasks(Pageable pageable){
        Page<TaskDto> tasks = taskService.getAll(pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<TaskDto>>builder()
                        .success(true)
                        .message("Tasks fetched")
                        .data(tasks)
                        .build()
        );
    }

    // ✅ FIX: TaskDto
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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDto taskDto) {

        TaskDto updated = taskService.updateTask(id, taskDto);

        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task updated")
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
                        .message("Task deleted")
                        .data(null)
                        .build()
        );
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Page<TaskDto>>> getTasksByStatus(
            @RequestParam Status status,
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.<Page<TaskDto>>builder()
                        .success(true)
                        .message("Filtered")
                        .data(taskService.getByStatus(status, pageable))
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<TaskDto>>> searchTasks(
            @RequestParam String title,
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.<Page<TaskDto>>builder()
                        .success(true)
                        .message("Search")
                        .data(taskService.searchByTitle(title, pageable))
                        .build()
        );
    }

    @GetMapping("/sorted")
    public ResponseEntity<ApiResponse<List<TaskDto>>> getSortedTasks(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        return ResponseEntity.ok(
                ApiResponse.<List<TaskDto>>builder()
                        .success(true)
                        .message("Sorted")
                        .data(taskService.getSortedTasks(sortBy, direction))
                        .build()
        );
    }

    @GetMapping("/admin/test")
    public String adminOnly(){
        return "Only ADMIN";
    }


    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestParam String refreshToken){

        RefreshToken token = refreshTokenService.validateToken(refreshToken);

        String newAccessToken = jwtService.generateToken(token.getUsername());

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
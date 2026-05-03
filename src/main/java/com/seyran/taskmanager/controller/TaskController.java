package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.ApiResponse;
import com.seyran.taskmanager.dto.PageResponse;
import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.service.TaskService;
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
        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task created")
                        .data(taskService.createTask(taskDto))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<TaskDto>>> getAllTasks(Pageable pageable){

        return ResponseEntity.ok(
                ApiResponse.<PageResponse<TaskDto>>builder()
                        .success(true)
                        .message("Tasks fetched")
                        .data(taskService.getAll(pageable)) // ✅ düz oldu
                        .build()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task found")
                        .data(taskService.getById(id))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDto taskDto) {

        return ResponseEntity.ok(
                ApiResponse.<TaskDto>builder()
                        .success(true)
                        .message("Task updated")
                        .data(taskService.updateTask(id, taskDto))
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

    @GetMapping("/admin/test")
    public String adminOnly(){
        return "Only ADMIN";
    }
}
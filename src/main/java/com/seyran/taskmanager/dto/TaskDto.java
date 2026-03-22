package com.seyran.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private String status;
}

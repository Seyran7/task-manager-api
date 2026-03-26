package com.seyran.taskmanager.dto;

import com.seyran.taskmanager.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "Task data transfer object")
public class TaskDto {
    @Schema(example = "Learn Spring Boot")
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @Schema(example = "Practice project")
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private Status status;
}

package com.seyran.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTask() throws Exception {
        TaskDto dto = new TaskDto();
        dto.setTitle("Test Task");

        when(taskService.createTask(dto)).thenReturn(dto);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Test Task"));
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        TaskDto dto = new TaskDto();
        dto.setTitle("Task 1");

        when(taskService.getAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(List.of(dto)));

        mockMvc.perform(get("/tasks?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].title").value("Task 1"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isOk());
    }
}
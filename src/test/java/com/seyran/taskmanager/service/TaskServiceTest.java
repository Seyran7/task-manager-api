package com.seyran.taskmanager.service;

import com.seyran.taskmanager.dto.TaskDto;
import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.entity.Task;
import com.seyran.taskmanager.mapper.TaskMapper;
import com.seyran.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() {
        Task task = new Task();
        task.setTitle("Test");

        TaskDto dto = new TaskDto();
        dto.setTitle("Test");

        when(taskRepository.findAll()).thenReturn(List.of(task));
        when(taskMapper.toDto(task)).thenReturn(dto);

        List<TaskDto> result = taskService.findAll();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getTitle());
    }

    @Test
    void shouldCreateTask() {
        TaskDto dto = new TaskDto();
        dto.setTitle("New Task");

        Task task = new Task();
        task.setTitle("New Task");

        when(taskMapper.toEntity(dto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(dto);

        TaskDto result = taskService.createTask(dto);

        assertEquals("New Task", result.getTitle());
    }

    @Test
    void shouldGetTasksWithPagination() {
        Task task = new Task();
        task.setTitle("Paged Task");

        TaskDto dto = new TaskDto();
        dto.setTitle("Paged Task");

        when(taskRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(task)));

        when(taskMapper.toDto(task)).thenReturn(dto);

        var result = taskService.getAll(PageRequest.of(0, 10));

        assertEquals(1, result.getContent().size());
    }

    @Test
    void shouldFilterByStatus() {
        Task task = new Task();
        task.setStatus(Status.TODO);

        when(taskRepository.findByStatus(eq(Status.TODO), any()))
                .thenReturn(new PageImpl<>(List.of(task)));

        var result = taskService.getByStatus(Status.TODO, PageRequest.of(0, 10));

        assertEquals(1, result.getContent().size());
    }

    @Test
    void shouldDeleteTask() {
        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }
}
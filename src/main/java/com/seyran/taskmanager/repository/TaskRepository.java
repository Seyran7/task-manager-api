package com.seyran.taskmanager.repository;

import com.seyran.taskmanager.entity.Status;
import com.seyran.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

    List<Task> findByTitleContainingIgnoreCase(String title);

}

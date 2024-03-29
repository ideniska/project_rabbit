package com.project_rabbit.Task;

import java.util.Optional;
import java.util.List;

public interface TaskService {
    Integer createTask(Task task);

    Optional<Task> findTask(Integer taskId);

    List<Task> findByProjectId(Integer projectId);

    Task updateTask(Integer taskId,
            Task task);

    List<Task> getAllTasks();

    void deleteTask(Integer taskId);
}

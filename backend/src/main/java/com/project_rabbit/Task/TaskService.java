package com.project_rabbit.Task;

import java.util.Optional;

public interface TaskService {
    Integer createTask(Task task);

    Optional<Task> findTask(Integer taskId);

    Task updateTask(Integer taskId,
            Task task);

    void deleteTask(Integer taskId);
}

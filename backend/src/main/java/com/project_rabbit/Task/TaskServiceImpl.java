package com.project_rabbit.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Integer createTask(Task task) {
        return taskRepository.save(task).getTaskId();
    }

    @Override
    public Optional<Task> findTask(Integer taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task updateTask(Integer taskId, Task task) {
        Task theTask = taskRepository.findById(taskId).stream().findFirst().orElseThrow(
                () -> new RuntimeException(String.format("task with id %s does not exist",
                        task.getTaskId())));

        theTask.setTitle(task.getTitle());
        theTask.setDescription(task.getDescription());
        theTask.setDueDate(task.getDueDate());
        theTask.setProjectId(task.getProjectId());
        theTask.setStatus(task.getStatus());
        theTask.setAssignedTo(task.getAssignedTo());

        return taskRepository.save(theTask);
    }

    @Override
    public void deleteTask(Integer taskId) {
        Task theTask = taskRepository.findById(taskId).get();
        if (theTask == null) {
            throw new RuntimeException(String.format("The task with id %s does not exist",
                    taskId));
        }
        taskRepository.delete(theTask);
    }
}

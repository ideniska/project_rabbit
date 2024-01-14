package com.project_rabbit.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Integer> createTask(@RequestBody Task task) {
        Integer taskId = taskService.createTask(task);
        return new ResponseEntity<>(taskId, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> findTask(@PathVariable("id") Integer taskId) {
        Task theTask = taskService.findTask(taskId).stream().findFirst().orElseThrow(() -> new RuntimeException(
                String.format("task with id %s does not exist", taskId)));

        return new ResponseEntity<>(theTask, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer taskId, @RequestBody Task task) {

        Task theTask = taskService.updateTask(taskId, task);
        return new ResponseEntity<>(theTask, HttpStatus.OK);
    }

    @DeleteMapping({ "id" })
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Integer taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

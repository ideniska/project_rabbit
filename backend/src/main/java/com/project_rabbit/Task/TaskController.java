package com.project_rabbit.Task;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Integer> createTask(@RequestBody Task task) {
        Integer taskId = taskService.createTask(task);
        return new ResponseEntity<>(taskId, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Task> findTask(@PathVariable("id") Integer taskId) {
        Task theTask = taskService.findTask(taskId).stream().findFirst().orElseThrow(() -> new RuntimeException(
                String.format("task with id %s does not exist", taskId)));

        return new ResponseEntity<>(theTask, HttpStatus.OK);
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer taskId, @RequestBody Task task) {

        Task theTask = taskService.updateTask(taskId, task);
        return new ResponseEntity<>(theTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Integer taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

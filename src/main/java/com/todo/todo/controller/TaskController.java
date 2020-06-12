package com.todo.todo.controller;

import com.todo.todo.model.Task;
import com.todo.todo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final String URL = "/api/tasks/";
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(URL)
    public ResponseEntity findAll() {
        List<Task> taskList = this.taskService.findAll();

        return ResponseEntity.ok(taskList);
    }

    @GetMapping(URL + "{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        Task taskById = taskService.findById(id);

        if (taskById == null) {
            return ResponseEntity.badRequest().body("Task id is not valid");
        }

        return ResponseEntity.ok(taskById);
    }

    @PostMapping(URL)
    public ResponseEntity createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);

        return createdTask == null
                ? ResponseEntity.badRequest().body("Provide a title")
                : ResponseEntity.ok(createdTask);
    }

    @DeleteMapping(URL + "{id}")
    public ResponseEntity deleteTask(@PathVariable("id") String id) {
        String deletedTaskId = taskService.deleteTaskById(id);

        return deletedTaskId == null
                ? ResponseEntity.badRequest().body("Wrong task id")
                : ResponseEntity.ok(deletedTaskId);
    }

    @PutMapping(URL)
    public ResponseEntity updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);

        return updatedTask == null
                ? ResponseEntity.badRequest().body("Something went wrong")
                : ResponseEntity.ok(updatedTask);
    }

}

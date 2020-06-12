package com.todo.todo.service;

import com.mongodb.client.result.UpdateResult;
import com.todo.todo.model.Task;
import com.todo.todo.repository.TaskRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private MongoTemplate mongoTemplate;

    public TaskService(TaskRepository taskRepository, MongoTemplate mongoTemplate) {
        this.taskRepository = taskRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(String id) {
        Optional<Task> taskById = taskRepository.findById(id);

        return taskById.isPresent() ? taskById.get() : null;
    }

    public String deleteTaskById(String taskId) {
        if (!taskRepository.existsById(taskId)) {
            return null;
        }

        taskRepository.deleteById(taskId);

        return taskId;
    }

    public Task createTask(Task task) {
        if (task.getTitle() == null) {
            return null;
        }

        if (task.getDone() == null) {
            task.setDone(false);
        }

        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        if ( task.getId() == null && !taskRepository.existsById(task.getId())) {
            return null;
        }

        Query query = new Query().addCriteria(Criteria.where("id").is(task.getId()));
        Update update = new Update();
        if (task.getDone() != null) {
            update.set("done", task.getDone());
        }

        if (task.getTitle() != null) {
            update.set("title", task.getTitle());
        }

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Task.class);

        if (updateResult.wasAcknowledged()) {
            return taskRepository.findById(task.getId()).get();
        }

        return null;
    }
}

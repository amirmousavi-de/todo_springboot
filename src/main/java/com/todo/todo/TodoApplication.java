package com.todo.todo;

import com.todo.todo.model.Task;
import com.todo.todo.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	private TaskRepository taskRepository;

	public TodoApplication(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Task t1 = new Task();
//		t1.setTitle("Sport");
//		t1.setDone(false);
//
//		Task t2 = new Task();
//		t2.setTitle("Study");
//		t2.setDone(true);
//
//		taskRepository.save(t1);
//		taskRepository.save(t2);
	}
}

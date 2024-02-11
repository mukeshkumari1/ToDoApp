package src.main.java.service;

import src.main.java.dto.Task;
import src.main.java.repository.TaskRepo;

import java.util.List;

public class TaskService {
    TaskRepo taskRepo = new TaskRepo();
    public boolean addTask(Task task) {
        return taskRepo.addTask(task);
    }

    public Task updateTask(int taskId) {
        return taskRepo.updateTask(taskId);
    }

    public boolean clone(int taskId) {
        return taskRepo.clone(taskId);
    }

    public List<Task> listAllTasks() {
        return taskRepo.listAllTasks();
    }

    public List<Task> searchByTaskStatus(String taskStatus) {
        return taskRepo.searchByTaskStatus(taskStatus);
    }

    public void searchTaskByTaskName(String taskName) {
         taskRepo.searchTaskByTaskName(taskName);
    }

    public void searchTaskByTaskDeadline(String taskDeadLine) {
          taskRepo.searchTaskByTaskDeadline(taskDeadLine);
    }

    public List<Task> sortTaskByTaskStatus() {
        return taskRepo.sortTasksByTaskStatus();
    }

    public List<Task> sortTaskByTaskStatusOrder() {
        return taskRepo.sortTasksByTaskStatusOrder();
    }

    public void searchTaskByTaskNameKeyword(String taskNameKey) {
        taskRepo.searchTaskByTaskNameKeyword(taskNameKey);
    }

    public void saveTasksInAFile() {
        taskRepo.saveTasksInAFile();
    }
}

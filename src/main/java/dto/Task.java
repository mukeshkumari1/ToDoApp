package src.main.java.dto;

import java.io.Serializable;

public class Task implements Serializable {
    private static int id = 0;
    private int taskId;
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private String taskDeadline;
    public Task(int taskId, String taskName, String taskDescription, TaskStatus taskStatus, String taskDeadline) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskDeadline = taskDeadline;
    }

    public static int getId() {
        ++id;
        return id;
    }
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskDeadline='" + taskDeadline + '\'' +
                '}';
    }
}

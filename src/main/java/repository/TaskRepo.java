package src.main.java.repository;

import src.main.java.dto.Task;
import src.main.java.dto.TaskStatus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepo {

    ArrayList<Task> taskDB = new ArrayList<>();

    public ArrayList<Task> getTaskDB() {
        return taskDB;
    }

    public boolean addTask(Task task) {
        return taskDB.add(task);
    }
    public Task updateTask(int taskId) {
        Task task = taskDB.get(taskId-1);
        if(task.getTaskStatus().equals(TaskStatus.INPROGRESS)){
            task.setTaskStatus(TaskStatus.DONE);
        } else {
            task.setTaskStatus(TaskStatus.INPROGRESS);
        }
        return task;
    }

    public boolean clone(int taskid) {
        Task existingTask = taskDB.get(taskid-1);
        System.out.println("Found the entered task id.");
        Task task = new Task(Task.getId(), existingTask.getTaskName(), existingTask.getTaskDescription(),
                existingTask.getTaskStatus(),existingTask.getTaskDeadline());
        return taskDB.add(task);
    }

    public List<Task> listAllTasks() {
        System.out.println("Task list is : "+ taskDB);
        for(Task t : taskDB){
            System.out.println("Task id : "+ t.getTaskId() + " task name : " + t.getTaskName() + " task status : " + t.getTaskStatus());
        }
        return taskDB;
    }

    public List<Task> searchByTaskStatus(String taskStatus) {
        List<Task> result = taskDB.stream()
                .filter(t -> t.getTaskStatus().name().equals(taskStatus))
                .collect(Collectors.toList());
        return  result;
               /* .forEach(task -> {
                    System.out.println(task.getTaskId() + " | " + task.getTaskName() + " | " + task.getTaskDescription()+ " | " + task.getTaskStatus()
                    + " | " + task.getTaskDeadline());
                });*/
    }
    public void searchTaskByTaskName(String taskName) {
        taskDB.stream()
                .filter(tn-> tn.getTaskName().equals(taskName))
                .forEach(task -> {
                    System.out.println(task.getTaskId() + " | " + task.getTaskName() + " | " + task.getTaskDescription()+ " | " + task.getTaskStatus()
                            + " | " + task.getTaskDeadline());
                });
    }

    public void searchTaskByTaskDeadline(String taskDeadline) {
        taskDB.stream()
                .filter(td-> td.getTaskDeadline().equals(taskDeadline))
                .forEach(task -> {
                    System.out.println(task.getTaskId() + " | " + task.getTaskName() + " | " + task.getTaskDescription()+ " | " + task.getTaskStatus()
                            + " | " + task.getTaskDeadline());
                });
        }

    public List<Task> sortTasksByTaskStatusOrder() {
        return taskDB.stream()
                .sorted(Comparator.comparing(Task::getTaskStatus))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByTaskStatus() {
        return taskDB.stream()
                .sorted(Comparator.comparing(Task::getTaskStatus, (o1, o2) -> o1.name().compareToIgnoreCase(o2.name())))
                .collect(Collectors.toList());
    }

    public void searchTaskByTaskNameKeyword(String taskNameKey) {
        taskDB.stream()
                .filter(tn-> tn.getTaskName().toLowerCase().contains(taskNameKey))
                .forEach(task -> {
                    System.out.println(task.getTaskId() + " | " + task.getTaskName() + " | " + task.getTaskDescription()+ " | " + task.getTaskStatus()
                            + " | " + task.getTaskDeadline());
                });
    }

    public void saveTasksInAFile() {
        OutputStreamWriter writer = null;
        try {
             writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\mukkumar28\\code\\Todo.txt"), StandardCharsets.UTF_8);
            for (Task t : taskDB) {
                writer.write(t.toString());
            }
            System.out.println("Tasks saved to file successfully!!");
            writer.close();
        } catch (IOException e) {
            System.out.println("Input/output Exception : " + e);
        }
    }
}

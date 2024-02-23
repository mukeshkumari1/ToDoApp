package src.main.java.controller;

import src.main.java.dto.Task;
import src.main.java.dto.TaskStatus;
import src.main.java.repository.TaskRepo;
import src.main.java.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class ToDoController {
    TaskService taskService = new TaskService();
    TaskRepo taskRepo = new TaskRepo();
    Scanner sc = new Scanner(System.in);

    public void runMyToDoApp(){
        while(true) {
            showMenu();
            String userAction = getUserInput("Enter the Action number you want to perform ");
            completeUserAction(userAction);
        }
    }

    private String getUserInput(String userInput) {
        System.out.print(userInput + " : ");
        return sc.nextLine();
    }

    private void completeUserAction(String action) {
        switch (action){
            case "1":
                addTask();
                for(Task t: taskRepo.getTaskDB()){
                    System.out.println("Task name is : " + t.getTaskName()
                            + " task id is : " + t.getTaskId()
                            + " task status is : " + t.getTaskStatus()
                            + " task deadline is : " + t.getTaskDeadline()
                            + " task description is : " + t.getTaskDescription()
                    );

                }
                break;
            case "2":
                updateTask();
                break;
            case "3":
                cloneTask();
                break;
            case "4":
                listAllTasks();
                break;
            case "5":
                searchTaskByTaskStatus();
                break;
            case "6":
                searchTaskByTaskName();
                break;
            case "7":
                searchTaskByTaskDeadline();
                break;
            case "8":
                sortTaskByTaskStatus();
                break;
            case "9":
                sortTaskByTaskStatusOrder();
                break;
            case "10":
                searchTaskByFuzzySearchingOnTaskName();
                break;
            case "11":
                saveTasksInAFile();
                break;
            case "12":
                System.exit(200);
            default:
                System.out.println("Enter the valid input !!!");
        }
    }

    private void saveTasksInAFile() {
        taskService.saveTasksInAFile();
    }

    private void searchTaskByFuzzySearchingOnTaskName() {
        String taskNameKey = getUserInput("Enter Keyword by which you want to search tasks");
        System.out.println("Task name key is : " + taskNameKey);
        printHeader();
        taskService.searchTaskByTaskNameKeyword(taskNameKey);
    }

    private void sortTaskByTaskStatus() {
        List<Task> sortedTasks = taskService.sortTaskByTaskStatus();
        printHeader();
        for(Task t : sortedTasks){
            System.out.println(t.getTaskId() +" | " + t.getTaskName() + " | " +t.getTaskDescription() +  " | "+ t.getTaskStatus() + " | " + t.getTaskDeadline());
        }
    }

    private void sortTaskByTaskStatusOrder() {
        List<Task> sortedTasks = taskService.sortTaskByTaskStatusOrder();
        printHeader();
        for(Task t : sortedTasks){
            System.out.println(t.getTaskId() +" | " + t.getTaskName() + " | " +t.getTaskDescription() +  " | "+ t.getTaskStatus() + " | " + t.getTaskDeadline());
        }
    }

    private void searchTaskByTaskDeadline() {
        String date = getUserInput("Enter Task Deadline for which you want to search tasks");
        System.out.println("Deadline is : " + date);
        printHeader();
        taskService.searchTaskByTaskDeadline(date);
    }
    private void searchTaskByTaskName() {
        String taskName = getUserInput("Enter Task Name which you want to search tasks");
        System.out.println("Task name is : " + taskName);
        printHeader();
        taskService.searchTaskByTaskName(taskName);
    }

    private void searchTaskByTaskStatus() {
        String input = getUserInput("Enter Task Status for which you want to search tasks");
        TaskStatus taskStatus = TaskStatus.valueOf(input);
        System.out.println("Task status is : " + taskStatus.name());
        printHeader();
        List<Task> nl = taskService.searchByTaskStatus(taskStatus.name());
        for(Task t : nl){
            System.out.println(t.getTaskId());
        }
    }
    private boolean cloneTask() {
        String input = getUserInput("Enter Task Id which you want to clone");
        int taskId = Integer.parseInt(input);
        return taskService.clone(taskId);
    }

    private void updateTask() {
        String input = getUserInput("Enter Task ID which you want to update");
        int taskId = Integer.parseInt(input);
        Task task = taskService.updateTask(taskId);
        System.out.println("Task id : " + task.getTaskId()
                + " Task status : "+ task.getTaskStatus());
    }

    private boolean addTask() {
        String taskName = getUserInput("Enter the Task name");
        String taskDescription = getUserInput("Enter the task Description[Optional field, Press Enter to skip] ");
        String taskDeadline = getUserInput(
                "Enter the Task Deadline in format as 01-Jan-2024 [Optional, Press Enter to skip]");
        Task newTask = new Task(Task.getId(), taskName, taskDescription, TaskStatus.PENDING, taskDeadline);
        return taskService.addTask(newTask);
    }
    private void listAllTasks() {
        List<Task> tasks = taskService.listAllTasks();
        printHeader();
        tasks.forEach(task -> {
            System.out.println(task.getTaskId() + " | " + task.getTaskName() + " | " + task.getTaskDescription() + " | " + task.getTaskStatus() + " | "
                    + task.getTaskDeadline());
        });
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    private static void printHeader() {
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("ID" + " | " + "TASK NAME" + " | " + "TASK DESCRIPTION" + " | " + "TASK STATUS" + " | " + "TASK DEADLINE");
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }

    private void showMenu() {
        System.out.println("Welcome to the ToDo App!!");
        System.out.println("Pick the action items : ");
        System.out.println("1. Add a task");
        System.out.println("2. Update a task");
        System.out.println("3. Clone a task");
        System.out.println("4. List All tasks");
        System.out.println("5. Search Task by Task Status");
        System.out.println("6. Search Task by Task name");
        System.out.println("7. Search Task by Task Deadline");
        System.out.println("8. Sort Task by Task Status [Done, In Progress, Pending]");
        System.out.println("9. Sort Task by Task status Order [Pending, In progress, Done]");
        System.out.println("10. Search Task by Fuzzy searching");
        System.out.println("11. Save tasks in file in json format");
        System.out.println("12. Exit..");
    }
}

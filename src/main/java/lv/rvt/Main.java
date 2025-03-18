package lv.rvt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.loadTasks();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                    System.out.println("Task added successfully!");
                    break;

                case 2:
                    ViewTask.displayTasks(taskManager);
                    break;

                case 3:
                    ViewTask.displayTasks(taskManager);
                    System.out.print("Enter task number to remove: ");
                    int index = scanner.nextInt();
                    taskManager.removeTask(index - 1);
                    System.out.println("Task removed successfully!");
                    break;

                case 4:
                    taskManager.saveTasks();
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}

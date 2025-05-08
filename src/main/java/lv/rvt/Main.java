package lv.rvt;

import java.util.*;

public class Main {
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.loadTasks();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTask(scanner, taskManager);
                case 2 -> viewAndEditTasks(scanner, taskManager);
                case 3 -> removeTask(scanner, taskManager);
                case 4 -> startTaskTimer(scanner);
                case 5 -> toggleTaskCompletion(scanner, taskManager);
                case 6 -> {
                    taskManager.saveTasks();
                    printBox("👋 Exiting... Goodbye!");
                    running = false;
                }
                default -> printBox(RED + "❌ Invalid choice!" + RESET);
            }
        }

        scanner.close();
    }

    private static void addTask(Scanner scanner, TaskManager manager) {
        printBox("➕ Add New Task");
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        manager.addTask(title, description);
        printBox(GREEN + "✅ Task added successfully!" + RESET);
    }

    private static void viewAndEditTasks(Scanner scanner, TaskManager manager) {
        boolean viewing = true;
        while (viewing) {
            ViewTask.displayTasks(manager);
            System.out.println(BLUE + "╔════════════════════════════════════════════╗");
            System.out.println("║ Enter task number to edit or 0 to go back  ║");
            System.out.println("╚════════════════════════════════════════════╝" + RESET);
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) return;

            if (choice > 0 && choice <= manager.getTasks().size()) {
                printBox("✏️  Editing Task #" + choice);
                System.out.print("New title: ");
                String newTitle = scanner.nextLine();
                System.out.print("New description: ");
                String newDesc = scanner.nextLine();
                manager.editTask(choice - 1, newTitle, newDesc);
                printBox(GREEN + "✅ Task updated!" + RESET);
            } else {
                printBox(RED + "❌ Invalid task number!" + RESET);
            }
        }
    }

    private static void removeTask(Scanner scanner, TaskManager manager) {
        ViewTask.displayTasks(manager);
        printBox("❌ Remove Task");
        System.out.print("Enter task number to remove or 0 to cancel: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index == 0) return;

        if (index > 0 && index <= manager.getTasks().size()) {
            manager.removeTask(index - 1);
            printBox(GREEN + "✅ Task removed successfully!" + RESET);
        } else {
            printBox(RED + "❌ Invalid task number!" + RESET);
        }
    }

    private static void toggleTaskCompletion(Scanner scanner, TaskManager manager) {
        ViewTask.displayTasks(manager);
        printBox("✅ Toggle Task Completion");
        System.out.print("Enter task number to toggle or 0 to cancel: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index == 0) return;

        if (index > 0 && index <= manager.getTasks().size()) {
            manager.toggleTaskCompletion(index - 1);
            printBox(GREEN + "✅ Task completion toggled!" + RESET);
        } else {
            printBox(RED + "❌ Invalid task number!" + RESET);
        }
    }

    private static void startTaskTimer(Scanner scanner) {
        printBox("⏳ Start Task Timer");
        System.out.print("Enter task duration in minutes: ");
        int minutes = scanner.nextInt();
        scanner.nextLine();

        int totalSeconds = minutes * 60;
        printBox("⏳ Timer started for " + minutes + " minutes! Type 'stop' to cancel.");

        Thread timerThread = new Thread(() -> {
            try {
                for (int i = totalSeconds; i > 0; i--) {
                    if (Thread.currentThread().isInterrupted()) return;
                    int min = i / 60;
                    int sec = i % 60;
                    System.out.printf("\rTime remaining: %02d:%02d", min, sec);
                    Thread.sleep(1000);
                }
                System.out.println();
                printBox(GREEN + "⏰ Time's up! Take a break!" + RESET);
            } catch (InterruptedException e) {
                System.out.println();
                printBox(RED + "❌ Timer stopped!" + RESET);
            }
        });

        timerThread.start();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("stop")) {
                timerThread.interrupt();
                break;
            }
        }

        printBox("⏳ Timer has ended! What would you like to do?");
        System.out.println("1. Start another timer");
        System.out.println("2. Return to the main menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> startTaskTimer(scanner);
            case 2 -> printBox("Returning to the main menu...");
            default -> printBox(RED + "❌ Invalid choice! Returning to the main menu." + RESET);
        }
    }

    private static void printMenu() {
        System.out.println(BLUE +
                "╔══════════════════════════════╗\n" +
                "║        📋 TO-DO LIST         ║\n" +
                "╠══════════════════════════════╣\n" +
                "║ 1. ➕ Add Task                ║\n" +
                "║ 2. ✏️  View & Edit Tasks       ║\n" +
                "║ 3. ❌ Remove Task             ║\n" +
                "║ 4. ⏳ Start Task Timer         ║\n" +
                "║ 5. ✅ Toggle Task Completed     ║\n" +
                "║ 6. 🚪 Exit                    ║\n" +
                "╚══════════════════════════════╝" + RESET);
        System.out.print("Enter your choice: ");
    }

    private static void printBox(String msg) {
        String plain = msg.replaceAll("\u001B\\[[;\\d]*m", "");
        String line = "═".repeat(plain.length());
        System.out.println(BLUE + "╔" + line + "╗");
        System.out.println("║" + msg + "║");
        System.out.println("╚" + line + "╝" + RESET);
    }
}

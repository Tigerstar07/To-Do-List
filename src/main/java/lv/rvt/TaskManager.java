package lv.rvt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import lv.rvt.tools.Helper;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String title, String description) {
        tasks.add(new Task(title, description));
    }

    public void removeTask(int index) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove!");
            return;
        }

        if (index >= 0 && index < tasks.size()) {
            System.out.println("Removing task: " + tasks.get(index).getTitle());
            tasks.remove(index);
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Add the editTask method here
    public void editTask(int index, String newTitle, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);        // Update the task title
            task.setDescription(newDescription);  // Update the task description
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public void saveTasks() {
        try (BufferedWriter writer = Helper.getWriter("tasks.csv", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Task task : tasks) {
                writer.write(task.getTitle() + "," + task.getDescription() + "," + task.isCompleted());
                writer.newLine();
            }
            System.out.println("✅ Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try (BufferedReader reader = Helper.getReader("tasks.csv")) {
            String line;
            System.out.println("📂 Loading tasks from file...");

            while ((line = reader.readLine()) != null) {
                System.out.println("📄 Read line: " + line); 

                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    Task task = new Task(parts[0], parts[1]);
                    if (Boolean.parseBoolean(parts[2])) {
                        task.markAsCompleted();
                    }
                    tasks.add(task);
                }
            }
            System.out.println("✅ Tasks loaded successfully!");
        } catch (IOException e) {
            System.out.println("⚠ No existing tasks found or error loading tasks: " + e.getMessage());
        }
    }
}

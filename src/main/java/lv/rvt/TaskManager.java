package lv.rvt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.dat";

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(String title, String description) {
        tasks.add(new Task(title, description));
    }

    public void editTask(int index, String newTitle, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            task.setDescription(newDescription);
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void toggleTaskCompletion(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).toggleCompleted();
        }
    }

    public void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("❌ Error saving tasks: " + e.getMessage());
        }

        saveTasksAsCSV("tasks.csv"); // also save as CSV
    }

    public void loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if file doesn't exist
        }
    }

    public void saveTasksAsCSV(String filename) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write("Title,Description,Created,Completed,CompletedTime\n");
            for (Task task : tasks) {
                writer.write(String.format("\"%s\",\"%s\",\"%s\",%s,\"%s\"\n",
                        task.getTitle().replace("\"", "\"\""),
                        task.getDescription().replace("\"", "\"\""),
                        task.getCreationDate(),
                        task.isCompleted(),
                        task.getCompletedTime()));
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving CSV: " + e.getMessage());
        }
    }
}

package lv.rvt;

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
    public void saveTasks() {
        // Exceptions pielietojums: java-mooc: 11-3
        try {
            BufferedWriter writer = Helper.getWriter("tasks.csv", StandardOpenOption.APPEND);
            // writer.write(123);

            for (Task task : tasks) {
                // ieraktīt failā
                // izmantojot writer objez
                writer.write(task.getTitle()+ "," + task.getDescription() + "," + task.isCompleted());

                System.out.println(
                    task.getTitle() + ", " + task.getDescription() // dzert, padzerties 3l ūdeni
                );
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

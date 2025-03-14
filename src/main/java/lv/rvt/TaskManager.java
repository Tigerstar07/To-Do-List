package lv.rvt;

import java.util.ArrayList;
import java.util.List;

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
}

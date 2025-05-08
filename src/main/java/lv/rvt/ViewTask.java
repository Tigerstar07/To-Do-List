package lv.rvt;

public class ViewTask {
    public static void displayTasks(TaskManager manager) {
        System.out.println("📋 Current Tasks:");
        if (manager.getTasks().isEmpty()) {
            System.out.println("❗ No tasks available.");
        } else {
            for (int i = 0; i < manager.getTasks().size(); i++) {
                Task task = manager.getTasks().get(i);
                String status = task.isCompleted()
                        ? "✅ Done at " + task.getCompletedTime()
                        : "❌ Not done";
                System.out.printf("%d. %s - %s [%s] | Status: %s%n",
                        i + 1, task.getTitle(), task.getDescription(),
                        task.getCreationDate(), status);
            }
        }
    }
}

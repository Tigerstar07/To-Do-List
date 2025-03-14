package lv.rvt;

public class ViewTask {
    public static void displayTasks(TaskManager taskManager) {
        System.out.println("\n--- Your Tasks ---");
        
        if (taskManager.getTasks().isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int index = 1;
        for (Task task : taskManager.getTasks()) {
            System.out.println(index + ". " + task);
            index++;
        }
    }
}

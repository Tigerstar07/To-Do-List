package lv.rvt;

import java.util.*;



public class ViewTask {
    public static void displayTasks(TaskManager taskManager) {
        System.out.println("\nYour Tasks:");
        int index = 1;
        for (Task task : taskManager.getTasks()) {
            System.out.println(index + ". " + task);
            index++;
        }
    }
}


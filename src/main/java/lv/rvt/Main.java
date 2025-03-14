package lv.rvt;

import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


    while (running){
        System.out.println("1. Add task");
        System.out.println("2. View task");
        System.out.println("3. Remove task");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice){
            case 1:
            System.out.println("Enter you new lists title");
            String title = scanner.nextLine();
            System.out.println("Enter the description of your task");
            String description = scanner.nextLine();
            taskManager.addTask(title, description);


        }
    }
    }
}
